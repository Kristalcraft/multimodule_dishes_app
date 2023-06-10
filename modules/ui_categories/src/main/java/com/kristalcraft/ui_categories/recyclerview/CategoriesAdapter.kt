package com.kristalcraft.ui_categories.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kristalcraft.datasource_categories.CategoryModel
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.ui_categories.databinding.CategoriesViewholderBinding

class CategoriesAdapterDelegate(val onCategoryClickListener: (name: String) -> Unit) : DelegateAdapter<CategoryModel, CategoriesAdapterDelegate.CategoryViewHolder>(CategoryModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CategoriesViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun bindViewHolder(
        model: CategoryModel,
        viewHolder: CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class CategoryViewHolder(private val binding: CategoriesViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel) {
            binding.categoryImage.load(item.image_url)
            binding.categoryName.text = item.name
            binding.root.setOnClickListener{onCategoryClickListener.invoke(item.name)}
        }
    }
}