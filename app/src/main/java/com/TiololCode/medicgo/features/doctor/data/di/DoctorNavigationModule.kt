package com.TiololCode.medicgo.features.doctor.data.di

import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.doctor.presentation.navigation.DoctorRouteRegistrar
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class DoctorNavigationModule {

    @Binds
    @IntoSet
    abstract fun bindDoctorRouteRegistrar(impl: DoctorRouteRegistrar): RouteRegistrar
}
