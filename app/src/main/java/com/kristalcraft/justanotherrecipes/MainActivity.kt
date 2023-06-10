package com.kristalcraft.justanotherrecipes

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.kristalcraft.justanotherrecipes.databinding.ActivityMainBinding
import com.kristalcraft.ui_categories.CategoriesFragment
import com.kristalcraft.ui_categories.CategoryClicked
import com.kristalcraft.ui_dishes.DishesFragment

class MainActivity : AppCompatActivity(), CategoryClicked {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setNavigation(savedInstanceState)
        openCategories(savedInstanceState)
    }

    override fun onBackPressed() {
        openCategories(null)
    }

    @SuppressLint("CommitTransaction")
    private fun openCategories(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, CategoriesFragment(), CATEGORIES)
                .commit()
        }
    }

    @SuppressLint("CommitTransaction")
    private fun openDishes(savedInstanceState: Bundle?, name: String) {
        if (savedInstanceState == null) {
            val fragment = DishesFragment()
            val bundle = Bundle()
            bundle.putString(DishesFragment.CATEGORY_NAME, name)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment, DISHES)
                .commit()
        }
    }

    private fun setNavigation(savedInstanceState: Bundle?) {
        val listener =
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main -> {openCategories(savedInstanceState)}
                    R.id.account -> { }
                    R.id.search -> { }
                    R.id.bucket -> { }
                }
                true
            }
        val navigation: BottomNavigationView = binding.navView
        navigation.setOnItemSelectedListener(listener)
    }

    companion object{
        const val CATEGORIES = "categories"
        const val DISHES = "dishes"
    }

    override fun onCategoryClicked(name: String) {
        openDishes(null, name)
    }

}