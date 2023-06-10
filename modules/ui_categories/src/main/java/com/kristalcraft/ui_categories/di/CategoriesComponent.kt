package com.kristalcraft.ui_categories.di

import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.ui_categories.CategoriesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope



@Component( modules = [CategoriesApiModule::class])
@CategoriesFeature
interface CategoriesComponent {

    fun inject(categoriesFragment: CategoriesFragment)


@Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): CategoriesComponent
    }
}

/*interface CategoriesComponentProvider{
    fun provide(): CategoriesComponent*/

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CategoriesFeature
