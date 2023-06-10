package com.kristalcraft.ui_categories.di

import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.ui_dishes.DishesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope



@Component( modules = [DishesApiModule::class])
@DishesFeature
interface DishesComponent {

    fun inject(dishesFragment: DishesFragment)


@Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): DishesComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DishesFeature
