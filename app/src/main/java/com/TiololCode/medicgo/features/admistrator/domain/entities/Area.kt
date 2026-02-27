package com.TiololCode.medicgo.features.admistrator.domain.entities

data class Area(
    val id: String,
    val name: String,
    val patientCount: Int,
    val criticos: Int = 0,
    val estables: Int = 0
)

