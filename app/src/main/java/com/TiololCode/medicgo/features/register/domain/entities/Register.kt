package com.TiololCode.medicgo.features.register.domain.entities

data class RegisteredUser(
    val id: Long,
    val name: String,
    val email: String,
    val licenseNumber: String,
    val specialty: String
)

data class RegisterResult(
    val message: String,
    val token: String,
    val user: RegisteredUser?
)

