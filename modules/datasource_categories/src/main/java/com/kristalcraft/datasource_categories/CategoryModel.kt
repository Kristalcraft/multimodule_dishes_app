package com.kristalcraft.datasource_categories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kristalcraft.delegate_adapter.DelegateAdapterItem

data class CategoryModel(
    @Expose
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("image_url")
    @Expose
    val image_url: String
) : DelegateAdapterItem {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return name
    }

}

