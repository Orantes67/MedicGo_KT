package com.TiololCode.medicgo.features.enfermero.data.di

import com.TiololCode.medicgo.core.di.MedicGoRetrofit
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.api.EnfermeroApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EnfermeroNetworkModule {

    @Provides
    @Singleton
    fun provideEnfermeroApi(@MedicGoRetrofit retrofit: Retrofit): EnfermeroApi {
        return retrofit.create(EnfermeroApi::class.java)
    }
}