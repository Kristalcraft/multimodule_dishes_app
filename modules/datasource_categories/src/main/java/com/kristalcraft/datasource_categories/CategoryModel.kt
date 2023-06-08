package com.kristalcraft.datasource_categories

import com.google.gson.annotations.Expose

data class CategoryModel(
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val image_url: String
)

