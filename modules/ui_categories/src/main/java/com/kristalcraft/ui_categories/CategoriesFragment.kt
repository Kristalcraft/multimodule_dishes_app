package com.kristalcraft.ui_categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristalcraft.delegate_adapter.MainCompositeAdapter
import com.kristalcraft.di_module.BaseApp
import com.kristalcraft.ui_categories.databinding.FragmentCategoriesBinding
import com.kristalcraft.ui_categories.di.CategoriesComponent
import com.kristalcraft.ui_categories.di.DaggerCategoriesComponent
import com.kristalcraft.ui_categories.recyclerview.CategoriesAdapterDelegate
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    private lateinit var categoriesComponent: CategoriesComponent
    private lateinit var binding: FragmentCategoriesBinding

    @Inject
    lateinit var viewModel: CategoriesViewModel

    private val adapter by lazy {
        MainCompositeAdapter.Builder()
            .add(CategoriesAdapterDelegate (categoryListener) )
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buildComponent()
        binding = FragmentCategoriesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoriesRecycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL , false )
        binding.categoriesRecycler.adapter = adapter


        viewModel.categoriesData.observe(viewLifecycleOwner){
            when (it) {
                is State.DataState -> adapter.submitList(it.data)
                is State.ErrorState -> {Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()}
                is State.LoadingState -> {Toast.makeText(context, "Загрузка...", Toast.LENGTH_SHORT).show()}
            }
        }
    }

    private fun buildComponent() {
        val appComponent = (context?.applicationContext as BaseApp).appComponent
        categoriesComponent = DaggerCategoriesComponent
            .builder()
            .appComponent(appComponent)
            .build()
        categoriesComponent.inject(this)
    }

    private val categoryListener = { name: String ->
        if (activity is CategoryClicked) {
            (activity as CategoryClicked).onCategoryClicked(name)
        }
    }
}

interface CategoryClicked {
    fun onCategoryClicked(name: String)
}