package com.TiololCode.medicgo.features.admistrator.data.datasource.remote.api

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AssignNurseRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.CreateProfessionalRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.HealthProfessionalsResponseDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.MetricsResponseDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AreaResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PATCH

interface AdminApi {
    @GET("v1/admin/metricas")
    suspend fun getMetrics(): MetricsResponseDto

    @GET("v1/admin/usuarios")
    suspend fun getHealthProfessionals(): HealthProfessionalsResponseDto

    @POST("v1/admin/usuarios")
    suspend fun createProfessional(@Body request: CreateProfessionalRequestDto): HealthProfessionalsResponseDto

    @PATCH("v1/admin/usuarios/asignar-enfermero")
    suspend fun assignNurse(@Body request: AssignNurseRequestDto): Void

    @GET("v1/admin/areas")
    suspend fun getAreas(): List<AreaResponseDto>

    @POST("v1/logout")
    suspend fun logout(): Void
}
