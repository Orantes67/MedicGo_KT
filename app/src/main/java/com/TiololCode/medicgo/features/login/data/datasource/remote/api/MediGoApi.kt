package com.TiololCode.medicgo.features.login.data.datasource.remote.api

import com.TiololCode.medicgo.features.login.data.datasource.remote.model.LoginRequest
import com.TiololCode.medicgo.features.login.data.datasource.remote.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponseDto
}
