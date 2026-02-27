package com.TiololCode.medicgo.features.enfermero.domain.entities

data class EnfermeroMetric(
    val total: Int,
    val critical: Int,
    val observation: Int,
    val stable: Int
)
