package com.kristalcraft.ui_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_dishes.DishModel
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor (private val categoriesApiHelper: DishesApiHelper) : ViewModel() {

    private val _dishData = MutableLiveData<State<DishModel>>()
     val dishData: LiveData<State<DishModel>> = _dishData


    fun getDish(id: Int) {
        _dishData.value = State.LoadingState
        viewModelScope.launch {
            categoriesApiHelper.getDish(id)
                .onEach {
                    _dishData.value = State.DataState(it)
                }
                .catch {
                    it.printStackTrace()
                    _dishData.value = State.ErrorState(it)
                }
                .launchIn(viewModelScope)
        }
    }

}


@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val dishesApiHelper: DishesApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(dishesApiHelper) as T
    }

}

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}