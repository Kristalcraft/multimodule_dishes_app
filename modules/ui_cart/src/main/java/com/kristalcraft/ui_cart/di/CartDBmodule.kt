package com.kristalcraft.ui_cart.di

import com.kristalcraft.datasource_cart.DishDao
import com.kristalcraft.di_module.di.AppComponent
import dagger.Module
import dagger.Provides

@Module
class CartDBmodule {

        @Provides
        @CartFeature
        fun provideDishDao(appComponent: AppComponent): DishDao {
            return appComponent.provideDishDao()
        }
}