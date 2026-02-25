package com.TiololCode.medicgo.features.admistrator.data.di

import com.TiololCode.medicgo.features.admistrator.data.repositories.AreasRepositoryImpl
import com.TiololCode.medicgo.features.admistrator.data.repositories.MetricsRepositoryImpl
import com.TiololCode.medicgo.features.admistrator.data.repositories.UsersRepositoryImpl
import com.TiololCode.medicgo.features.admistrator.domain.repositories.AreasRepository
import com.TiololCode.medicgo.features.admistrator.domain.repositories.MetricsRepository
import com.TiololCode.medicgo.features.admistrator.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMetricsRepository(
        metricsRepositoryImpl: MetricsRepositoryImpl
    ): MetricsRepository

    @Binds
    abstract fun bindUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository

    @Binds
    abstract fun bindAreasRepository(
        areasRepositoryImpl: AreasRepositoryImpl
    ): AreasRepository
}

