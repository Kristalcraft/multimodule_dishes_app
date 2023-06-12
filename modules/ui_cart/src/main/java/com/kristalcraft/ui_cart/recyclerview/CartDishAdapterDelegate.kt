package com.kristalcraft.ui_cart.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ui_cart.databinding.CartDishViewholderBinding
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem


class CartDishAdapterDelegate(val onCounterClickListener: (id: Int,difference:Int) -> Unit) : DelegateAdapter<com.kristalcraft.datasource_cart.CartModel, CartDishAdapterDelegate.CategoryViewHolder>(
    com.kristalcraft.datasource_cart.CartModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CartDishViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun bindViewHolder(
        model: com.kristalcraft.datasource_cart.CartModel,
        viewHolder: CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class CategoryViewHolder(private val binding: CartDishViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: com.kristalcraft.datasource_cart.CartModel) {
            binding.dishImage.load(item.image_url)
            binding.dishName.text = item.name
            binding.counterCount.text = item.count.toString()
            binding.dishPrice.text = "${item.price} ₽"
            binding.dishWeight.text = " · ${item.weight}г"
            binding.counterMinus.setOnClickListener {
                onCounterClickListener.invoke(item.id,-1)
            }
            binding.counterPlus.setOnClickListener {
                onCounterClickListener.invoke(item.id,1)
            }
        }
    }
}