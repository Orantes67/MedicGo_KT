package com.TiololCode.medicgo.core.di

import com.TiololCode.medicgo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @MedicGoRetrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MEDICGO_API + "/")
            .callFactory(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}