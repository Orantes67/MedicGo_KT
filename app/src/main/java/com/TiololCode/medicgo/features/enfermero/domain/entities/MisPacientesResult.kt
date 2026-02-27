package com.TiololCode.medicgo.features.enfermero.domain.entities

data class MisPacientesResult(
    val metrics: EnfermeroMetric,
    val patients: List<EnfermeroPatient>
)
