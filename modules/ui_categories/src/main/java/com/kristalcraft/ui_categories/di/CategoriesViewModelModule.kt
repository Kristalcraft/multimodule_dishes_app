package com.kristalcraft.ui_categories.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.ui_categories.CategoriesViewModel
import com.kristalcraft.ui_categories.ViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class CategoriesViewModelModule {

    @Provides
    @CategoriesFeature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): CategoriesViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[CategoriesViewModel::class.java]
    }

    @Provides
    @CategoriesFeature
    fun provideFactory(categoriesApiHelper: CategoriesApiHelper): ViewModelFactory {
        return ViewModelFactory(categoriesApiHelper)
    }

}