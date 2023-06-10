package com.kristalcraft.di_module.di

import android.content.Context
import com.kristalcraft.di_module.BaseApp
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Scope


@Main
@Component(modules = [NetworkModule::class])
interface AppComponent{

    fun inject(app: BaseApp)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

    fun provideRetrofit(): Retrofit

}
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Main
