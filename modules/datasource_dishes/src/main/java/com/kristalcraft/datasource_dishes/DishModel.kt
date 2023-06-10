package com.kristalcraft.dishes_datasourse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kristalcraft.delegate_adapter.DelegateAdapterItem

data class DishModel(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("price")
    val price: Float,
    @Expose
    @SerializedName("weight")
    val weight: Float,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("image_url")
    val image_url: String,
    @Expose
    @SerializedName("tegs")
    val tags: List<String>,
) : DelegateAdapterItem {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return name
    }

}

