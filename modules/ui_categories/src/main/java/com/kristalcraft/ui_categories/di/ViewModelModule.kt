package com.kristalcraft.ui_categories.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.ui_categories.CategoriesViewModel
import com.kristalcraft.ui_categories.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Provides
    //@Feature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): CategoriesViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[CategoriesViewModel::class.java]
    }

    @Provides
    //@Feature
    fun provideFactory(categoriesApiHelper: CategoriesApiHelper): ViewModelFactory {
        return ViewModelFactory(categoriesApiHelper)
    }

}