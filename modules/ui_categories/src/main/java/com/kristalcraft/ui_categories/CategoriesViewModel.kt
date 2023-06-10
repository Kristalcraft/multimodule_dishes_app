package com.kristalcraft.ui_categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.datasource_categories.CategoryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class CategoriesViewModel @Inject constructor (private val categoriesApiHelper: CategoriesApiHelper) : ViewModel() {

    val _categoriesData = MutableLiveData<State<List<CategoryModel>>>()
    val categoriesData : LiveData<State<List<CategoryModel>>> = _categoriesData

    init{
        viewModelScope.launch {
            getCategories()
        }
    }

    suspend fun getCategories() {
        _categoriesData.value = State.LoadingState
        categoriesApiHelper.getCategories()
            .onEach { _categoriesData.value = State.DataState(it) }
            .catch { it.printStackTrace()
                _categoriesData.value = State.ErrorState(it) }
            .launchIn(viewModelScope)
    }
}

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory (private val categoriesApiHelper: CategoriesApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriesViewModel(categoriesApiHelper) as T
    }

}

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}