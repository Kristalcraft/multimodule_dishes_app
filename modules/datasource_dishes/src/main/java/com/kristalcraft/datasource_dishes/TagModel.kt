package com.kristalcraft.datasource_dishes

import com.kristalcraft.delegate_adapter.DelegateAdapterItem

data class TagModel(
    val name:String
) : DelegateAdapterItem {
    override fun id(): Any {
        return name
    }

    override fun content(): Any {
        return name
    }
}
