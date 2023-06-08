package com.kristalcraft.datasource_categories

import kotlinx.coroutines.flow.Flow

interface CategoriesApiHelper {

    fun getCategories(): Flow<ArrayList<CategoryModel>>

}