package com.TiololCode.medicgo.features.register.data.datasource.remote.api

import com.TiololCode.medicgo.features.register.data.datasource.remote.model.RegisterRequestDto
import com.TiololCode.medicgo.features.register.data.datasource.remote.model.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("v1/register")
    suspend fun register(@Body request: RegisterRequestDto): RegisterResponseDto
}

