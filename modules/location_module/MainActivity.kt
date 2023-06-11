package com.kristalcraft.justanotherrecipes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.kristalcraft.justanotherrecipes.databinding.ActivityMainBinding
import com.kristalcraft.location_module.CityProvider
import com.kristalcraft.ui_categories.CategoriesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainHandler:Handler
    private val onCityFoundListener:(String) -> Unit = { it: String ->
        mainHandler.post {
            Toast.makeText(this.applicationContext, "yout city is $it!", Toast.LENGTH_SHORT).show()
        }
    }
    private val getCityRegistration = CityProvider.registerPermsToGetCity(this, onCityFoundListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainHandler = Handler(this.mainLooper)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setNavigation(savedInstanceState)
        openCategories(savedInstanceState)
    }

    @SuppressLint("CommitTransaction")
    private fun openCategories(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, CategoriesFragment(), CATEGORIES)
                .commit()
        }
    }

    private fun setNavigation(savedInstanceState: Bundle?) {
        val listener =
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main -> {openCategories(savedInstanceState)}
                    R.id.account -> {
                        CityProvider.getCity(this,getCityRegistration,onCityFoundListener)
                    }
                    R.id.search -> {                  }
                    R.id.search -> {                  }
                }
                true
            }
        val navigation: BottomNavigationView = binding.navView
        navigation.setOnItemSelectedListener(listener)
    }

    companion object{
        const val CATEGORIES = "categories"
    }

}