package com.kristalcraft.ui_categories

import com.kristalcraft.datasource_categories.CategoriesApi
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.datasource_categories.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesApiHelperImpl @Inject constructor(val apiService: CategoriesApi):
    CategoriesApiHelper {

    override suspend fun getCategories(): Flow<List<CategoryModel>> = flow {
        emit(apiService.getCategories().categories)
    }


}
