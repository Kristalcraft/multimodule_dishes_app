package com.kristalcraft.di_module

import android.app.Application
/*
import com.kristalcraft.justanotherrecipes.di.AppComponent
import com.kristalcraft.justanotherrecipes.di.DaggerAppComponent
import com.kristalcraft.ui_categories.di.CategoriesComponent
import com.kristalcraft.ui_categories.di.CategoriesComponentProvider

class App: Application(), CategoriesComponentProvider {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .applicationContext(applicationContext)
            .build()
        appComponent.inject(this)

    }

    override fun provide(): CategoriesComponent {
        return appComponent.categoriesComponent().create()

    }

}*/
