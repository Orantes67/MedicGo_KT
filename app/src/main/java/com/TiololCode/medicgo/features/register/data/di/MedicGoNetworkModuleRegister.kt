package com.TiololCode.medicgo.features.register.data.di

import com.TiololCode.medicgo.core.di.MedicGoRetrofit
import com.TiololCode.medicgo.features.register.data.datasource.remote.api.RegisterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedicGoNetworkModuleRegister {

    @Provides
    @Singleton
    fun provideRegisterApi(@MedicGoRetrofit retrofit: Retrofit): RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }
}