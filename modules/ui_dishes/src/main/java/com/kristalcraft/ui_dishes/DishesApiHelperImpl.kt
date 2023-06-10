package com.kristalcraft.ui_dishes

import com.kristalcraft.dishes_datasourse.DishModel
import com.kristalcraft.dishes_datasourse.DishesApi
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DishesApiHelperImpl @Inject constructor(val apiService: DishesApi):
    DishesApiHelper {

    override suspend fun getDishes(tag: String): Flow<List<DishModel>> = flow {
        val prefferedDishes = arrayListOf<DishModel>()
        apiService.getDishes().dishes.forEach {
            if (it.tags.contains(tag)){
                prefferedDishes.add(it)
            }
        }
        emit(prefferedDishes)

    }


}
