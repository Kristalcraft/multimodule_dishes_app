package com.kristalcraft.di_module

import android.app.Application
import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.di_module.di.DaggerAppComponent

open class BaseApp: Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .applicationContext(applicationContext)
            .build()
        //appComponent.inject(this)

    }

}
