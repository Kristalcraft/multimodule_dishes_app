package com.kristalcraft.dishes_datasourse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kristalcraft.datasource_dishes.DishModel

data class DishesResponse (
    @Expose
    @SerializedName("dishes")
    val dishes: List<DishModel>
    )
