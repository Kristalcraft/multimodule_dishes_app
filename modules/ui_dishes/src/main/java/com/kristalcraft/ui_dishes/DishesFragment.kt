package com.kristalcraft.ui_dishes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.delegate_adapter.MainCompositeAdapter
import com.kristalcraft.di_module.BaseApp
import com.kristalcraft.ui_categories.di.DaggerDishesComponent
import com.kristalcraft.ui_categories.di.DishesComponent
import com.kristalcraft.ui_categories.recyclerview.DishesAdapterDelegate
import com.kristalcraft.ui_dishes.databinding.FragmentDishesBinding
import com.kristalcraft.ui_dishes.recyclerview.TagsAdapterDelegate
import javax.inject.Inject


class DishesFragment : Fragment() {

    private lateinit var dishesComponent: DishesComponent
    private lateinit var binding: FragmentDishesBinding

    @Inject
    lateinit var viewModel: DishesViewModel

    private val dishesAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(DishesAdapterDelegate())
            .build()
    }

    private val tagsAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(TagsAdapterDelegate())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buildComponent()
        binding = FragmentDishesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCategory()

        binding.dishesRecycler.layoutManager =
            GridLayoutManager(this.context, 3,GridLayoutManager.VERTICAL, false)
        binding.dishesRecycler.adapter = dishesAdapter

        viewModel.dishData.observe(viewLifecycleOwner){
            when (it) {
                is State.DataState -> dishesAdapter.submitList(it.data)
                is State.ErrorState -> {
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()}
                is State.LoadingState -> {
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()}
            }
        }

        binding.tagsRecycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.tags.observe(viewLifecycleOwner){
            tagsAdapter.submitList(it.toList())
        }
    }

    private fun setCategory() {
        arguments.let {
            binding.category.text = it?.getString(CATEGORY_NAME)
        }
    }

    private fun buildComponent() {
        val appComponent = (context?.applicationContext as BaseApp).appComponent
        dishesComponent = DaggerDishesComponent
            .builder()
            .appComponent(appComponent)
            .build()
        dishesComponent.inject(this)
    }

    companion object{
        const val CATEGORY_NAME = "category_name"
    }
}


