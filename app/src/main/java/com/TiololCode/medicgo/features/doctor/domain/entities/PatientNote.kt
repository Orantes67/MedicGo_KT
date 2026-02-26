package com.TiololCode.medicgo.features.doctor.domain.entities

data class PatientNote(
    val id: Long,
    val patientId: Long,
    val doctorId: Long,
    val content: String,
    val createdDate: String
)

