package com.TiololCode.medicgo.features.admistrator.domain.entities

data class Metric(
    val id: Long,
    val totalPatients: Int,
    val criticalPatients: Int,
    val observationPatients: Int
)

