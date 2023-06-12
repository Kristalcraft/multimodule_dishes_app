package com.kristalcraft.ui_cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_cart.CartModel
import com.kristalcraft.datasource_cart.DishDao
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class CartViewModel @Inject constructor (private val dishesApiHelper: DishesApiHelper,
                                         private val dishDao: DishDao
) : ViewModel() {

    private val _cart = MutableLiveData<List<CartModel>>()
    val cart :LiveData<List<CartModel>> = _cart
    private val _sum = MutableLiveData<Int>()
    val sum :LiveData<Int> = _sum

    init{
        getCart()
    }

    fun getCart() {
        dishDao.getDishCart()
            ?.onEach {
                _cart.value = it
            }
            ?.onEach {
                _sum.value = it.sumOf { cart ->
                    cart.price * cart.count
                }
            }
            ?.catch {
                it.message?.let { it1 -> Log.e("__DEBUG__", it1) }
            }
            ?.launchIn(viewModelScope)
    }

    fun addToCart(id: Int, diff: Int){
        viewModelScope.launch (Dispatchers.IO){
            val cart = dishDao.getDishCart(id)
            cart?.let{
                it.count += diff
                if (it.count < 0){
                    it.count = 0
                }
                dishDao.update(it)
            }

        }
    }
}


@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory (private val cartApiHelper: DishesApiHelper, private val dishDao: DishDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartApiHelper, dishDao) as T
    }
}