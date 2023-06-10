package com.kristalcraft.ui_categories.di

import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.dishes_datasourse.DishesApi
import com.kristalcraft.dishes_datasourse.DishesApiHelper
import com.kristalcraft.ui_dishes.DishesApiHelperImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DishesApiModule {

    @Provides
    @DishesFeature
    fun provideRetrofit(appComponent: AppComponent): Retrofit {
        return appComponent.provideRetrofit()
    }

    @Provides
    @DishesFeature
    fun provideApi(retrofit: Retrofit): DishesApi {
        return retrofit.create(DishesApi::class.java)
    }

    @Provides
    @DishesFeature
    fun provideApiHelperImpl(categoriesApi: DishesApi): DishesApiHelper {
        return DishesApiHelperImpl(categoriesApi)
    }


}