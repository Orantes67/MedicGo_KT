package com.TiololCode.medicgo.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MedicGoRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RegisterQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AdministratorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DoctorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PatientApiRetrofit