package com.kristalcraft.ui_cart.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristalcraft.datasource_cart.DishDao
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import com.kristalcraft.ui_cart.CartViewModel
import com.kristalcraft.ui_cart.ViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class CartViewModelModule {

    @Provides
    @CartFeature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): CartViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[CartViewModel::class.java]
    }

    @Provides
    @CartFeature
    fun provideFactory(dishesApiHelper: DishesApiHelper, dishDao: DishDao): ViewModelFactory {
        return ViewModelFactory(dishesApiHelper, dishDao)
    }

}