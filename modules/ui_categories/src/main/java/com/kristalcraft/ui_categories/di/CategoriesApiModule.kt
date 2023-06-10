package com.kristalcraft.ui_categories.di

import com.kristalcraft.datasource_categories.CategoriesApi
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.ui_categories.CategoriesApiHelperImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CategoriesApiModule {

    @Provides
    @CategoriesFeature
    fun provideRetrofit(appComponent: AppComponent): Retrofit {
        return appComponent.provideRetrofit()
    }

    @Provides
    @CategoriesFeature
    fun provideApi(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Provides
    @CategoriesFeature
    fun provideApiHelperImpl(categoriesApi: CategoriesApi): CategoriesApiHelper {
        return CategoriesApiHelperImpl(categoriesApi)
    }


}