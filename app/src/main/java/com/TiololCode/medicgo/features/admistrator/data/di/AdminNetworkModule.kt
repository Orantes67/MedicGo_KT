package com.TiololCode.medicgo.features.admistrator.data.di

import com.TiololCode.medicgo.core.di.AdministratorQualifier
import com.TiololCode.medicgo.core.di.MedicGoRetrofit
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.api.AdminApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdminNetworkModule {

    @Provides
    @Singleton
    @AdministratorQualifier
    fun provideAdminApi(@MedicGoRetrofit retrofit: Retrofit): AdminApi {
        return retrofit.create(AdminApi::class.java)
    }
}

