package com.TiololCode.medicgo.features.enfermero.data.datasource.remote.mapper

import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.EnfermeroPatientItemDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.EnfermeroStatsDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.MisPacientesResponseDto
import com.TiololCode.medicgo.features.enfermero.domain.entities.EnfermeroMetric
import com.TiololCode.medicgo.features.enfermero.domain.entities.EnfermeroPatient
import com.TiololCode.medicgo.features.enfermero.domain.entities.MisPacientesResult

fun MisPacientesResponseDto.toDomain(): MisPacientesResult = MisPacientesResult(
    metrics = stats?.toDomain() ?: EnfermeroMetric(0, 0, 0, 0),
    patients = patients?.map { it.toDomain() } ?: emptyList()
)

fun EnfermeroStatsDto.toDomain(): EnfermeroMetric = EnfermeroMetric(
    total       = total       ?: 0,
    critical    = critico     ?: 0,
    observation = observacion ?: 0,
    stable      = estable     ?: 0
)

fun EnfermeroPatientItemDto.toDomain(): EnfermeroPatient = EnfermeroPatient(
    id               = id               ?: 0L,
    code             = code             ?: "",
    fullName         = fullName         ?: "",
    age              = age              ?: 0,
    area             = area             ?: "",
    currentState     = currentState     ?: "",
    conditionNote    = conditionNote    ?: "",
    lastUpdate       = lastUpdate       ?: "",
    nextAppointment  = nextAppointment  ?: ""
)
