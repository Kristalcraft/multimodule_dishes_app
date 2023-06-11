package com.kristalcraft.ui_cart.recyclerview

import com.kristalcraft.datasource_dishes.DishModel
import com.kristalcraft.delegate_adapter.DelegateAdapterItem

data class CartDishModel(
    val dishModel: DishModel,
    val count: Int = 0
) : DelegateAdapterItem{
    override fun id(): Any =
        dishModel.id


    override fun content(): Any {
        return "${dishModel.content()}$count"
    }


}