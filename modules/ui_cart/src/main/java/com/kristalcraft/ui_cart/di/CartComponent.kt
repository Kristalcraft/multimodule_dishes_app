package com.kristalcraft.ui_cart.di

import com.kristalcraft.di_module.di.AppComponent
import com.kristalcraft.ui_cart.CartFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope



@Component( modules = [CartApiModule::class, CartDBmodule::class])
@CartFeature
interface CartComponent {

    fun inject(dishesFragment: CartFragment)


@Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): CartComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CartFeature
