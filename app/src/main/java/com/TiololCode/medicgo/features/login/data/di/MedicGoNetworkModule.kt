package com.TiololCode.medicgo.features.login.data.di

import com.TiololCode.medicgo.core.di.MedicGoRetrofit
import com.TiololCode.medicgo.features.login.data.datasource.remote.api.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedicGoNetworkModule {

    @Provides
    @Singleton
    fun provideLoginApi(@MedicGoRetrofit retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}
