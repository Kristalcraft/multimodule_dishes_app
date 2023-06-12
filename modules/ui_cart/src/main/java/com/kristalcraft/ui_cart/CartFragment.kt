package com.kristalcraft.ui_cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui_cart.databinding.FragmentCartBinding
import com.kristalcraft.datasource_cart.CartModel
import com.kristalcraft.delegate_adapter.MainCompositeAdapter
import com.kristalcraft.di_module.BaseApp
import com.kristalcraft.ui_cart.di.CartComponent
import com.kristalcraft.ui_cart.di.DaggerCartComponent
import com.kristalcraft.ui_cart.recyclerview.CartDishAdapterDelegate
import javax.inject.Inject

class CartFragment: Fragment() {

    private lateinit var cartComponent: CartComponent
    private lateinit var binding: FragmentCartBinding

    @Inject
    lateinit var viewModel: CartViewModel

    private val cartAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(CartDishAdapterDelegate(onAddClick))
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buildComponent()
        binding = FragmentCartBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cartRecycler.layoutManager =
           LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.cartRecycler.adapter = cartAdapter

        viewModel.cart.observe(viewLifecycleOwner){
            val list = ArrayList<CartModel>()
            it.forEach {thisCart->
                list.add(
                    thisCart.copy()
                )
            }
            cartAdapter.submitList(list.toList())
        }

        viewModel.getCart()
    }


    private fun buildComponent() {
        val appComponent = (context?.applicationContext as BaseApp).appComponent
        cartComponent = DaggerCartComponent
            .builder()
            .appComponent(appComponent)
            .build()
        cartComponent.inject(this)
    }

    val onAddClick = { id: Int, diff: Int ->
        viewModel.addToCart(id, diff)
    }

}