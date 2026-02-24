package com.TiololCode.medicgo.features.login.data.di

import com.TiololCode.medicgo.features.login.data.repositories.LoginRepositoryImpl
import com.TiololCode.medicgo.features.login.domain.repositories.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}
