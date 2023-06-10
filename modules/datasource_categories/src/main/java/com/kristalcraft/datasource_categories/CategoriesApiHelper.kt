package com.kristalcraft.datasource_categories

import kotlinx.coroutines.flow.Flow

interface CategoriesApiHelper {

    suspend fun getCategories(): Flow<List<CategoryModel>>

}