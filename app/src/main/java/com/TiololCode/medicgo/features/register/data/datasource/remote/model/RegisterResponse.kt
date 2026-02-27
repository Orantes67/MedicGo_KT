package com.TiololCode.medicgo.features.register.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    @SerializedName("name") val name: String,
    @SerializedName("license_number") val licenseNumber: String,
    @SerializedName("specialty") val specialty: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val role: String
)

data class RegisteredUserDto(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("license_number") val licenseNumber: String?,
    @SerializedName("specialty") val specialty: String?
)

data class RegisterResponseDto(
    @SerializedName("message") val message: String? = null,
    @SerializedName("token") val token: String? = null,
    @SerializedName("user") val user: RegisteredUserDto? = null
)

