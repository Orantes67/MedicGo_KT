package com.TiololCode.medicgo.features.login.domain.entities

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val licenseNumber: String
)

data class LoginResult(
    val message: String,
    val token: String,
    val user: User?
)
