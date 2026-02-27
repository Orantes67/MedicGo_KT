package com.TiololCode.medicgo.features.doctor.domain.entities

data class PatientNote(
    val id: Long,
    val author: String,
    val date: String,
    val content: String
)

