package com.TiololCode.medicgo.features.doctor.domain.entities

data class DoctorPatient(
    val id: Long,
    val code: String,
    val fullName: String,
    val age: Int,
    val area: String,
    val currentState: String,       // "critico" | "observacion" | "estable"
    val conditionNote: String,
    val assignedNurse: String,
    val lastUpdate: String,
    val nextAppointment: String
)

