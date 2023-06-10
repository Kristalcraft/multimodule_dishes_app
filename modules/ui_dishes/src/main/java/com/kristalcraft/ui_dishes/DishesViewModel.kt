package com.kristalcraft.ui_dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_dishes.TagModel
import com.kristalcraft.dishes_datasourse.DishModel
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class DishesViewModel @Inject constructor (private val categoriesApiHelper: DishesApiHelper) : ViewModel() {

    private val _dishData = MutableLiveData<State<List<DishModel>>>()
    val dishData : LiveData<State<List<DishModel>>> = _dishData
    private val _tags = MutableLiveData<ArrayList<TagModel>>()
    val tags : LiveData<ArrayList<TagModel>> = _tags
    private val stringTags = ArrayList<String>()

    init{
        viewModelScope.launch {
            getDishes("Все меню")
        }

    }

    suspend fun getDishes(tag: String) {
        val tags = ArrayList<String>()
        _dishData.value = State.LoadingState
        categoriesApiHelper.getDishes(tag)
            .onEach {
                _dishData.value = State.DataState(it)
                it.forEach { dish ->
                    dish.tags.forEach {dishTag ->
                            if (!tags.contains(dishTag)){
                                tags.add(dishTag)
                            }
                    }
                }
            }
            .catch { it.printStackTrace()
                _dishData.value = State.ErrorState(it) }
            .launchIn(viewModelScope)


    }


    private fun collectTags(tags: List<DishModel>){

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