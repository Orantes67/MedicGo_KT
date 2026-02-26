package com.TiololCode.medicgo.features.doctor.domain.entities

data class Doctor(
    val id: Long,
    val name: String,
    val lastName: String,
    val specialty: String,
    val registrationDate: String
)

