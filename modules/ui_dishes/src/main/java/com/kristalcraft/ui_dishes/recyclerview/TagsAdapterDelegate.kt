package com.kristalcraft.ui_dishes.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristalcraft.datasource_dishes.TagModel
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.ui_dishes.databinding.TagViewholderBinding

class TagsAdapterDelegate : DelegateAdapter<TagModel, TagsAdapterDelegate.TagViewHolder>(TagModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = TagViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun bindViewHolder(
        model: TagModel,
        viewHolder: TagViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class TagViewHolder(private val binding: TagViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TagModel) {
            binding.tagName.text = item.name

        }
    }
}