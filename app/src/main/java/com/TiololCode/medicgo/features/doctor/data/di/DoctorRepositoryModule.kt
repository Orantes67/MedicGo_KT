package com.TiololCode.medicgo.features.doctor.data.di

import com.TiololCode.medicgo.features.doctor.data.repositories.DoctorRepositoryImpl
import com.TiololCode.medicgo.features.doctor.data.repositories.PatientRepositoryImpl
import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DoctorRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDoctorRepository(
        doctorRepositoryImpl: DoctorRepositoryImpl
    ): DoctorRepository

    @Binds
    @Singleton
    abstract fun bindPatientRepository(
        patientRepositoryImpl: PatientRepositoryImpl
    ): PatientRepository
}

