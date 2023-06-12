package com.kristalcraft.ui_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.kristalcraft.di_module.BaseApp
import com.kristalcraft.datasource_dishes.DishModel
import com.kristalcraft.ui_details.databinding.FragmentDetailesBinding
import com.kristalcraft.ui_details.di.DaggerDetailsComponent
import com.kristalcraft.ui_details.di.DetailsComponent
import javax.inject.Inject

class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailesBinding
    private lateinit var detailsComponent: DetailsComponent
    var dishId: Int? = null

    @Inject
    lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buildComponent()
        binding = FragmentDetailesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(DISH_ID)?.let {
            dishId = it
            viewModel.getDish(it)
        }

        binding.detailsClose.setOnClickListener { activity?.onBackPressed() }
        binding.detailsAdd.setOnClickListener {
            dishId?.let { it1 -> viewModel.addToCart(it1) }
            Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show()
        }

        viewModel.dishData.observe(viewLifecycleOwner){
            when (it) {
                is State.DataState -> {
                    fillDetails(it.data)
                    binding.popupContainer.visibility = View.VISIBLE
                }
                is State.ErrorState -> {
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()}
                is State.LoadingState -> {
                    Toast.makeText(context, "Загрузка...", Toast.LENGTH_SHORT).show()
                    binding.popupContainer.visibility = View.INVISIBLE
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillDetails(data: DishModel) {
        binding.apply {
            dishDescription.text = data.description
            dishImage.load( data.image_url)
            dishName.text = data.name
            dishPrice.text = "${data.price} ₽"
            dishWeight.text = " · ${data.weight}г"
        }
    }

    private fun buildComponent() {
        val appComponent = (context?.applicationContext as BaseApp).appComponent
        detailsComponent = DaggerDetailsComponent
            .builder()
            .appComponent(appComponent)
            .build()
        detailsComponent.inject(this)
    }

    companion object{
        const val DISH_ID = "dish_id"
    }

}

interface CartClicked {
    fun onAddClicked(name: String)
}