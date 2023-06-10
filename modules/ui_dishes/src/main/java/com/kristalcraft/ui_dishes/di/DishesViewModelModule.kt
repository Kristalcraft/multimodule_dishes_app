package com.kristalcraft.ui_categories.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import com.kristalcraft.ui_dishes.DishesViewModel
import com.kristalcraft.ui_dishes.ViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class DishesViewModelModule {

    @Provides
    @DishesFeature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): DishesViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[DishesViewModel::class.java]
    }

    @Provides
    @DishesFeature
    fun provideFactory(dishesApiHelper: DishesApiHelper): ViewModelFactory {
        return ViewModelFactory(dishesApiHelper)
    }

}