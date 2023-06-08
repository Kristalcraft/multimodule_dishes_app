package com.kristalcraft.ui_categories

import com.kristalcraft.datasource_categories.CategoriesApi
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesApiHelperImpl @Inject constructor(val apiService: CategoriesApi): CategoriesApiHelper {

    override fun getCategories() = flow{
        emit(apiService.getCategories())
    }
}