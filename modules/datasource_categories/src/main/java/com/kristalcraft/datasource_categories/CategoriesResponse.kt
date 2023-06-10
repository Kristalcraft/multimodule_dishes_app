package com.kristalcraft.datasource_categories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoriesResponse (
    @Expose
    @SerializedName("сategories")
    //what the hell russian "c"?
    val categories: List<CategoryModel>
    )