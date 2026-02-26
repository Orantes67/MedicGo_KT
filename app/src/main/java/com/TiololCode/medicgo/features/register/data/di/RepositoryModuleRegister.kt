package com.TiololCode.medicgo.features.register.data.di

import com.TiololCode.medicgo.features.register.data.repositories.RegisterRepositoryImpl
import com.TiololCode.medicgo.features.register.domain.repositories.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleRegister {

    @Binds
    abstract fun bindRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ): RegisterRepository
}
