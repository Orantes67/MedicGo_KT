package com.TiololCode.medicgo.features.enfermero.data.datasource.remote.api

import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.AddEnfermeroNotaRequestDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.AddEnfermeroNotaResponseDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.MisPacientesResponseDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.UpdateEnfermeroEstadoRequestDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.UpdateEnfermeroEstadoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface EnfermeroApi {

    @GET("v1/enfermeros/mis-pacientes")
    suspend fun getMisPacientes(): MisPacientesResponseDto

    @PATCH("v1/enfermeros/pacientes/{id}/estado")
    suspend fun updatePacienteEstado(
        @Path("id") pacienteId: Long,
        @Body request: UpdateEnfermeroEstadoRequestDto
    ): UpdateEnfermeroEstadoResponseDto

    @POST("v1/enfermeros/pacientes/{id}/notas")
    suspend fun addNotaRapida(
        @Path("id") pacienteId: Long,
        @Body request: AddEnfermeroNotaRequestDto
    ): AddEnfermeroNotaResponseDto
}
