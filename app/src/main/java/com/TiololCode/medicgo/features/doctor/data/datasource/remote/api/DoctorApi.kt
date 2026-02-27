package com.TiololCode.medicgo.features.doctor.data.datasource.remote.api

import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.AddNoteRequestDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.AddNoteResponseDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.MyPatientsResponseDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.PatientDetailDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.UpdateStateRequestDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.UpdateStateResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface DoctorApi {

    /** GET /api/v1/doctores/mis-pacientes */
    @GET("v1/doctores/mis-pacientes")
    suspend fun getMyPatients(): MyPatientsResponseDto

    /** GET /api/v1/doctores/pacientes/{id} */
    @GET("v1/doctores/pacientes/{id}")
    suspend fun getPatientDetail(@Path("id") patientId: Long): PatientDetailDto

    /** PATCH /api/v1/doctores/pacientes/{id}/estado */
    @PATCH("v1/doctores/pacientes/{id}/estado")
    suspend fun updatePatientState(
        @Path("id") patientId: Long,
        @Body request: UpdateStateRequestDto
    ): UpdateStateResponseDto

    /** POST /api/v1/doctores/pacientes/{id}/notas */
    @POST("v1/doctores/pacientes/{id}/notas")
    suspend fun addPatientNote(
        @Path("id") patientId: Long,
        @Body request: AddNoteRequestDto
    ): AddNoteResponseDto
}
