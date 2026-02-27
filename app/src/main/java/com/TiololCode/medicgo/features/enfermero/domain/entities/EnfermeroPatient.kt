package com.TiololCode.medicgo.features.enfermero.domain.entities

data class EnfermeroPatient(
    val id: Long,
    val code: String,
    val fullName: String,
    val age: Int,
    val area: String,
    val currentState: String,
    val conditionNote: String,
    val lastUpdate: String,
    val nextAppointment: String
)
