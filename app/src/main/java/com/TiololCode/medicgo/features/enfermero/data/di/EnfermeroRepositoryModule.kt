package com.TiololCode.medicgo.features.enfermero.data.di

import com.TiololCode.medicgo.features.enfermero.data.repositories.EnfermeroRepositoryImpl
import com.TiololCode.medicgo.features.enfermero.domain.repositories.EnfermeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EnfermeroRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEnfermeroRepository(
        impl: EnfermeroRepositoryImpl
    ): EnfermeroRepository
}