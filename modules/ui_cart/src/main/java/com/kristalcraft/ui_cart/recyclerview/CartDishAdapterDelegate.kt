package com.kristalcraft.ui_cart.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ui_cart.databinding.CartDishViewholderBinding
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem


class CartDishAdapterDelegate(val onCounterClickListener: (id: Int,difference:Int) -> Unit) : DelegateAdapter<CartDishModel, CartDishAdapterDelegate.CategoryViewHolder>(CartDishModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CartDishViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun bindViewHolder(
        model: CartDishModel,
        viewHolder: CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class CategoryViewHolder(private val binding: CartDishViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartDishModel) {
            binding.dishImage.load(item.dishModel.image_url)
            binding.dishName.text = item.dishModel.name

            binding.dishPrice.text = "${item.dishModel.price} ₽ · ${item.dishModel.weight}г"
            binding.counterMinus.setOnClickListener {
                onCounterClickListener.invoke(item.dishModel.id,-1)
            }
            binding.counterPlus.setOnClickListener {
                onCounterClickListener.invoke(item.dishModel.id,1)
            }
        }
    }
}