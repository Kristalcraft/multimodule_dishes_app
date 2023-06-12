package com.kristalcraft.ui_details.di

import com.kristalcraft.datasource_cart.DishDao
import com.kristalcraft.di_module.di.AppComponent
import dagger.Module
import dagger.Provides

@Module
class DishDBmodule {

        @Provides
        @DishesFeature
        fun provideDishDao(appComponent: AppComponent): DishDao {
            return appComponent.provideDishDao()
        }
}