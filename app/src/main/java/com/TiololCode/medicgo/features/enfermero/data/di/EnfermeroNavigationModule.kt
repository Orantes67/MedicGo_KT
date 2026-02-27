package com.TiololCode.medicgo.features.enfermero.data.di

import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.enfermero.presentation.navigation.EnfermeroRouteRegistrar
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class EnfermeroNavigationModule {

    @Binds
    @IntoSet
    abstract fun bindEnfermeroRouteRegistrar(
        impl: EnfermeroRouteRegistrar
    ): RouteRegistrar
}