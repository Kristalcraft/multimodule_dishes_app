package com.kristalcraft.ui_dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_dishes.TagModel
import com.kristalcraft.datasource_dishes.DishModel
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class DishesViewModel @Inject constructor (private val dishesApiHelper: DishesApiHelper) : ViewModel() {

    private val _dishData = MutableLiveData<State<List<DishModel>>>()
    val dishData : LiveData<State<List<DishModel>>> = _dishData
    private val _tags = MutableLiveData<ArrayList<TagModel>>()
    val tags : LiveData<ArrayList<TagModel>> = _tags
    private val collectedTags = ArrayList<String>()
    private var firstResponse = true

    init{
        getDishes(ALL)
        selectTag(ALL)
    }

    fun getDishes(tag: String) {
        _dishData.value = State.LoadingState
        viewModelScope.launch {
            dishesApiHelper.getDishes(tag)
                .onEach {
                    if (firstResponse ) {
                        collectTags(it)
                        firstResponse = false
                    }
                    _dishData.value = State.DataState(it)
                }
                .catch {
                    it.printStackTrace()
                    _dishData.value = State.ErrorState(it)
                }
                .onCompletion { selectTag(tag) }
                .launchIn(viewModelScope)
        }
    }


    private fun collectTags(dishes: List<DishModel>){
        dishes.forEach{
            it.tags.forEach {dishTag ->
                if (!collectedTags.contains(dishTag)){
                    collectedTags.add(dishTag)
                }
            }
        }
        val tagModels = ArrayList<TagModel>()
        collectedTags.forEach {
            tagModels.add(TagModel(it))
        }

        _tags.value = tagModels
    }

    fun selectTag(name: String){
        val tagModels = tags.value
        tagModels?.forEach {
            it.selected = it.name == name
        }
        tagModels?.let{
            _tags.value = it
        }
    }

    companion object{
        const val ALL = "Все меню"
    }
}


@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory (private val dishesApiHelper: DishesApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DishesViewModel(dishesApiHelper) as T
    }

}

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}