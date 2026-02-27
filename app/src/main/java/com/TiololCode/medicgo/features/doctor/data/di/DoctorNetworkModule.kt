package com.TiololCode.medicgo.features.doctor.data.di

import com.TiololCode.medicgo.core.di.MedicGoRetrofit
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.api.DoctorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DoctorNetworkModule {

    @Provides
    @Singleton
    fun provideDoctorApi(@MedicGoRetrofit retrofit: Retrofit): DoctorApi {
        return retrofit.create(DoctorApi::class.java)
    }
}
