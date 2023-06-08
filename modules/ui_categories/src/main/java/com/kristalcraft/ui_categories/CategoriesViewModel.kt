package com.kristalcraft.ui_categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.datasource_categories.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class CategoriesViewModel @Inject constructor (private val categoriesApiHelper: CategoriesApiHelper) : ViewModel() {

    init{
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            categoriesApiHelper.getCategories()
                .flowOn(Dispatchers.IO)
                .catch { e ->

                }
                .collect { list ->
                    list.forEach {
                        Log.d("__DEBUG__", it.name)
                    }

                }
        }
    }
}

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory (private val categoriesApiHelper: CategoriesApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriesViewModel(categoriesApiHelper) as T
    }

}