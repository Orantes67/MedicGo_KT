package com.TiololCode.medicgo.features.doctor.domain.entities

data class DoctorMetric(
    val totalPatients: Int,
    val patientsUnderObservation: Int,
    val criticalPatients: Int,
    val stablePatients: Int
)

