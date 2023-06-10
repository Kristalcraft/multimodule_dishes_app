package com.kristalcraft.dishes_datasourse

import kotlinx.coroutines.flow.Flow

interface DishesApiHelper {

    suspend fun getDishes(tag: String): Flow<List<DishModel>>

}