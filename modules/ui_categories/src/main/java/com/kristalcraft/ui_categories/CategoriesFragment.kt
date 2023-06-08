package com.kristalcraft.ui_categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import retrofit2.Retrofit
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    /*private lateinit var categoriesComponent: CategoriesComponent

    @Inject
    lateinit var viewModel: CategoriesViewModel

    @Inject
    lateinit var retrofit: Retrofit*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val appComponent = (context?.applicationContext as App)



        //categoriesComponent.inject(this)

        // Creates a new instance of LoginComponent
        // Injects the component to populate the @Inject fields



        //viewModel.getCategories()
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }



}