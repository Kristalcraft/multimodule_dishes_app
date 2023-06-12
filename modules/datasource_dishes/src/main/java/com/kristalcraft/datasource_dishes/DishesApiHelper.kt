package com.kristalcraft.dishes_datasourse

import com.kristalcraft.datasource_dishes.DishModel
import kotlinx.coroutines.flow.Flow

interface DishesApiHelper {

    fun getDishes(tag: String): Flow<List<DishModel>>

    fun getDish(id: Int): Flow<DishModel>

}