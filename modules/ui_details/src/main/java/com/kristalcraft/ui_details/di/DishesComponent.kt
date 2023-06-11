package com.kristalcraft.ui_details.di

import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.ui_details.DetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope



@Component( modules = [DishesApiModule::class])
@DishesFeature
interface DetailsComponent {

    fun inject(detailsFragment: DetailsFragment)


@Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): DetailsComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DishesFeature
