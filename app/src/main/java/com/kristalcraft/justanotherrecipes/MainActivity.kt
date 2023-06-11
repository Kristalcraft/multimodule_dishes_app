package com.kristalcraft.justanotherrecipes

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.kristalcraft.justanotherrecipes.databinding.ActivityMainBinding
import com.kristalcraft.ui_categories.CategoriesFragment
import com.kristalcraft.ui_categories.CategoryClicked
import com.kristalcraft.ui_details.DetailsFragment
import com.kristalcraft.ui_dishes.DishClicked
import com.kristalcraft.ui_dishes.DishesFragment

class MainActivity : AppCompatActivity(), CategoryClicked, DishClicked {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        setContentView(view)


        setNavigation()
        openCategories()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack( DETAILS, 1)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("CommitTransaction")
    private fun openCategories() {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, CategoriesFragment(), CATEGORIES)
                .commit()
    }

    @SuppressLint("CommitTransaction")
    private fun openDishes( name: String) {
            val fragment = DishesFragment()
            val bundle = Bundle()
            bundle.putString(DishesFragment.CATEGORY_NAME, name)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment, DISHES)
                .commit()
    }

    @SuppressLint("CommitTransaction")
    private fun openDetails(id: Int){
        /*supportFragmentManager.beginTransaction()
            .find
            .replace(binding.fullFragmentContainer.id, fragment, DETAILS)
            .addToBackStack(DETAILS)
            .commit()*/

        val fragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putInt(DetailsFragment.DISH_ID, id)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(binding.fullFragmentContainer.id, fragment, DETAILS)
            .addToBackStack(DETAILS)
            .commit()
    }

    private fun setNavigation() {
        val listener =
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main -> {openCategories()}
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
        const val DETAILS = "details"
    }

    override fun onCategoryClicked(name: String) {
        openDishes(name)
    }

    override fun onDishClicked(dishId: Int) {
        openDetails(dishId)
    }

}