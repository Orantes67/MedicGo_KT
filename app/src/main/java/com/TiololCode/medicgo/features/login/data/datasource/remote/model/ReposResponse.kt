package com.TiololCode.medicgo.features.login.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("license_number") val licenseNumber: String,
    @SerializedName("password") val password: String
)

data class UserDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("license_number") val licenseNumber: String?,
    @SerializedName("role") val role: String?
)

data class LoginResponseDto(
    @SerializedName("message") val message: String? = null,
    @SerializedName("token") val token: String? = null,
    @SerializedName("user") val user: UserDto?
)
