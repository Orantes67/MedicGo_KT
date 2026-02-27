package com.TiololCode.medicgo.features.admistrator.data.datasource.remote.api

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AssignPatientRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.CreatePatientRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.PatientCreatedResponseDto
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientApi {

    @POST("v1/pacientes")
    suspend fun createPatient(@Body request: CreatePatientRequestDto): PatientCreatedResponseDto

    @PATCH("v1/pacientes/{id}/asignar")
    suspend fun assignPatient(
        @Path("id") patientId: String,
        @Body request: AssignPatientRequestDto
    ): Any
}
