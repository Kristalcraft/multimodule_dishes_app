package com.kristalcraft.datasource_cart

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kristalcraft.delegate_adapter.DelegateAdapterItem


@Entity(
    indices = [
        Index(value = ["id"])
    ]
)
data class CartModel(
    @Expose
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("price")
    val price: Int,
    @Expose
    @SerializedName("weight")
    val weight: Int,
    @Expose
    @SerializedName("image_url")
    val image_url: String,
    @Expose
    @SerializedName("count")
    var count: Int = 0,
) : DelegateAdapterItem {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return "$name$count"
    }

}