package com.TiololCode.medicgo.features.doctor.domain.entities

data class DoctorMetric(
    val total: Int,
    val critical: Int,
    val observation: Int,
    val stable: Int
)

