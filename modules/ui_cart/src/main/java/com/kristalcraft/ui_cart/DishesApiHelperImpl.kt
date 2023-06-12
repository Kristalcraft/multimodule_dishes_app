package com.kristalcraft.ui_cart

import com.kristalcraft.datasource_dishes.DishModel
import com.kristalcraft.dishes_datasourse.DishesApi
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DishesApiHelperImpl @Inject constructor(val apiService: DishesApi):
    DishesApiHelper {

    override fun getDishes(tag: String): Flow<List<DishModel>> = flow {
        val prefferedDishes = arrayListOf<DishModel>()
        apiService.getDishes().dishes.forEach {
            if (it.tags.contains(tag)){
                prefferedDishes.add(it)
            }
        }
        emit(prefferedDishes)

    }

    override fun getDish(id: Int): Flow<DishModel> = flow {
        apiService.getDishes().dishes.forEach {
            if (it.id == id) {
                emit(it)
            }
        }

    }


}
