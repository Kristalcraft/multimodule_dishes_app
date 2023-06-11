package com.kristalcraft.dishes_datasourse

import com.kristalcraft.datasource_dishes.DishModel
import kotlinx.coroutines.flow.Flow

interface DishesApiHelper {

    suspend fun getDishes(tag: String): Flow<List<DishModel>>

    suspend fun getDish(id: Int): Flow<DishModel>

}