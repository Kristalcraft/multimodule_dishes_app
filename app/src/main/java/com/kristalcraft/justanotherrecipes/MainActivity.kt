package com.kristalcraft.justanotherrecipes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.kristalcraft.justanotherrecipes.databinding.ActivityMainBinding
import com.kristalcraft.location_module.CityProvider
import com.kristalcraft.ui_cart.CartFragment
import com.kristalcraft.ui_categories.CategoriesFragment
import com.kristalcraft.ui_categories.CategoryClicked
import com.kristalcraft.ui_details.DetailsFragment
import com.kristalcraft.ui_dishes.DishClicked
import com.kristalcraft.ui_dishes.DishesFragment
import java.text.DateFormat

class MainActivity : AppCompatActivity(), CategoryClicked, DishClicked {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainHandler: Handler
    private val onCityFoundListener:(String) -> Unit = { it: String ->
        mainHandler.post {
            binding.topBar.location.text = it
        }
    }
    private val getCityRegistration = CityProvider.registerPermsToGetCity(this, onCityFoundListener)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        setContentView(view)
        mainHandler = Handler(mainLooper)

        CityProvider.getCityIfGranted(this@MainActivity,onCityFoundListener)
        setTopBar()
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
        setTopBar()
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
        val fragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putInt(DetailsFragment.DISH_ID, id)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(binding.fullFragmentContainer.id, fragment, DETAILS)
            .addToBackStack(DETAILS)
            .commit()
    }

    @SuppressLint("CommitTransaction")
    private fun openCart(){
        setTopBar()
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, CartFragment(), CART)
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
                    R.id.bucket -> {openCart() }
                }
                true
            }
        val navigation: BottomNavigationView = binding.navView
        navigation.setOnItemSelectedListener(listener)
    }

    private fun setTopBar(categoryName:String = "") {
        binding.topBar.apply {
            val shouldShowCategory = categoryName.isNotEmpty()
            backButton.visibility = if (shouldShowCategory) View.VISIBLE else View.INVISIBLE
            category.visibility = if (shouldShowCategory) View.VISIBLE else View.INVISIBLE
            locationIcon.visibility = if (shouldShowCategory) View.INVISIBLE else View.VISIBLE
            location.visibility = if (shouldShowCategory) View.INVISIBLE else View.VISIBLE
            date.visibility = if (shouldShowCategory) View.INVISIBLE else View.VISIBLE
            category.text = categoryName
            backButton.setOnClickListener { openCategories() }
            locationIcon.setOnClickListener { CityProvider.getCity(this@MainActivity,getCityRegistration,onCityFoundListener) }


            val simpleDateFormat =  DateFormat.getDateInstance(DateFormat.LONG).format(System.currentTimeMillis())
            date.text = simpleDateFormat
        }

    }

    companion object{
        const val CATEGORIES = "categories"
        const val DISHES = "dishes"
        const val DETAILS = "details"
        const val CART = "cart"
    }

    override fun onCategoryClicked(name: String) {
        setTopBar(name)
        openDishes(name)
    }

    override fun onDishClicked(dishId: Int) {
        openDetails(dishId)
    }

}