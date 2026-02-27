package com.TiololCode.medicgo.features.login.data.di

import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.login.presentation.navigation.LoginRouteRegistrar
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginNavigationModule {

    @Binds
    @IntoSet
    abstract fun bindLoginRouteRegistrar(impl: LoginRouteRegistrar): RouteRegistrar
}
