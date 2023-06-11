package com.kristalcraft.ui_categories.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.dishes_datasourse.DishModel
import com.kristalcraft.ui_dishes.databinding.DishViewholderBinding

class DishesAdapterDelegate(val onDishClickLister: (id: Int) -> Unit) : DelegateAdapter<DishModel, DishesAdapterDelegate.CategoryViewHolder>(DishModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DishViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun bindViewHolder(
        model: DishModel,
        viewHolder: CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class CategoryViewHolder(private val binding: DishViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DishModel) {
            binding.dishImage.load(item.image_url)
            binding.dishName.text = item.name
            binding.root.setOnClickListener { onDishClickLister(item.id) }
        }
    }
}