package com.kristalcraft.ui_cart.di

import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.dishes_datasourse.DishesApi
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import com.kristalcraft.ui_cart.DishesApiHelperImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CartApiModule {

    @Provides
    @CartFeature
    fun provideRetrofit(appComponent: AppComponent): Retrofit {
        return appComponent.provideRetrofit()
    }

    @Provides
    @CartFeature
    fun provideApi(retrofit: Retrofit): DishesApi {
        return retrofit.create(DishesApi::class.java)
    }

    @Provides
    @CartFeature
    fun provideApiHelperImpl(categoriesApi: DishesApi): DishesApiHelper {
        return DishesApiHelperImpl(categoriesApi)
    }


}