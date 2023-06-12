package com.kristalcraft.ui_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_cart.CartModel
import com.kristalcraft.datasource_cart.DishDao
import com.kristalcraft.datasource_dishes.DishModel
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor (private val categoriesApiHelper: DishesApiHelper, private val dishDao: DishDao) : ViewModel() {

    private val _dishData = MutableLiveData<State<DishModel>>()
     val dishData: LiveData<State<DishModel>> = _dishData


    fun getDish(id: Int) {
        _dishData.value = State.LoadingState
        categoriesApiHelper.getDish(id)
            .onEach {
                _dishData.value = State.DataState(it)
            }
            .flowOn(Dispatchers.Main)
            .onEach {
                addToDB(it)
            }
            .flowOn(Dispatchers.IO)
            .catch {
                it.printStackTrace()
                _dishData.value = State.ErrorState(it)
            }
            .launchIn(viewModelScope)
    }

    fun addToCart(id: Int){
        viewModelScope.launch (Dispatchers.IO){
            val cart = dishDao.getDishCart(id)
            cart?.let{
                it.count++
                dishDao.update(cart)
            }

        }
    }

    fun addToDB(dishModel: DishModel) {
        viewModelScope.launch {
            if ( dishDao.getDishCart(dishModel.id) == null){
                dishDao.insert(mapDishToCart(dishModel))
            }
        }
    }

    private fun mapDishToCart(dish: DishModel, count: Int = 0): CartModel{
        return CartModel(
            dish.id,
            dish.name,
            dish.price,
            dish.weight,
            dish.image_url,
            count
        )
    }

}


@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val dishesApiHelper: DishesApiHelper, private val dishDao: DishDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(dishesApiHelper, dishDao) as T
    }

}

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}