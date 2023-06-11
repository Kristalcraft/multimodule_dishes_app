package com.kristalcraft.ui_details.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import com.kristalcraft.ui_details.DetailsViewModel
import com.kristalcraft.ui_details.ViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class DetailsViewModelModule {

    @Provides
    @DishesFeature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): DetailsViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[DetailsViewModel::class.java]
    }

    @Provides
    @DishesFeature
    fun provideFactory(dishesApiHelper: DishesApiHelper): ViewModelFactory {
        return ViewModelFactory(dishesApiHelper)
    }

}