package com.kristalcraft.ui_dishes.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristalcraft.datasource_dishes.TagModel
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.ui_dishes.databinding.TagViewholderBinding

class TagsAdapterDelegate(val onTagClickListener: (tag: String) -> Unit) : DelegateAdapter<TagModel, TagsAdapterDelegate.TagViewHolder>(TagModel::class.java) {

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
            if (item.selected){
                binding.root.setCardBackgroundColor(binding.root.context.getColor(com.kristalcraft.resource_module.R.color.figma_blue))
                binding.tagName.setTextColor(Color.WHITE)
            } else {
                binding.tagName.setTextColor(Color.BLACK)
                binding.root.setCardBackgroundColor(binding.root.context.getColor(com.kristalcraft.resource_module.R.color.figma_card_background))
            }
            binding.root.setOnClickListener { onTagClickListener.invoke(item.name)}
        }
    }
}