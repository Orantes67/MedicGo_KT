package com.TiololCode.medicgo.features.register.data.di

import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.register.presentation.navigation.RegisterRouteRegistrar
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class RegisterNavigationModule {

    @Binds
    @IntoSet
    abstract fun bindRegisterRouteRegistrar(impl: RegisterRouteRegistrar): RouteRegistrar
}
