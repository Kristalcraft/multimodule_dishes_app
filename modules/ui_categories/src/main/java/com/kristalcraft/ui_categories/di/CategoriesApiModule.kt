package com.kristalcraft.ui_categories.di

import com.kristalcraft.datasource_categories.CategoriesApi
import com.kristalcraft.datasource_categories.CategoriesApiHelper
import com.kristalcraft.ui_categories.CategoriesApiHelperImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CategoriesApiModule {

    /*@Provides
    @Feature
    fun provideRetrofit(appComponent: AppComponent): Retrofit {
        return appComponent.
    }*/

    @Provides
    //@Feature
    fun provideApi(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Provides
    //@Feature
    fun provideApiHelperImpl(categoriesApi: CategoriesApi): CategoriesApiHelper {
        return CategoriesApiHelperImpl(categoriesApi)
    }


}