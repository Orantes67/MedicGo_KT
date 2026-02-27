package com.TiololCode.medicgo.features.admistrator.data.di

import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.admistrator.presentation.navigation.AdminRouteRegistrar
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class AdminNavigationModule {

    @Binds
    @IntoSet
    abstract fun bindAdminRouteRegistrar(impl: AdminRouteRegistrar): RouteRegistrar
}
