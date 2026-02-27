package com.TiololCode.medicgo.features.doctor.data.datasource.remote.mapper

import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.AddNoteResponseDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.MyPatientsResponseDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.PatientDetailDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.PatientItemDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.PatientNoteDto
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.MyPatientsResult
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientDetail
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote

fun MyPatientsResponseDto.toDomain(): MyPatientsResult {
    val metrics = DoctorMetric(
        total       = stats?.total       ?: 0,
        critical    = stats?.critico     ?: 0,
        observation = stats?.observacion ?: 0,
        stable      = stats?.estable     ?: 0
    )
    return MyPatientsResult(
        metrics  = metrics,
        patients = patients?.map { it.toDomain() } ?: emptyList()
    )
}

fun PatientItemDto.toDomain(): DoctorPatient = DoctorPatient(
    id              = id              ?: 0L,
    code            = code            ?: "",
    fullName        = fullName        ?: "",
    age             = age             ?: 0,
    area            = area            ?: "",
    currentState    = currentState    ?: "",
    conditionNote   = conditionNote   ?: "",
    assignedNurse   = assignedNurse   ?: "",
    lastUpdate      = lastUpdate      ?: "",
    nextAppointment = nextAppointment ?: ""
)

fun PatientDetailDto.toDomain(): PatientDetail = PatientDetail(
    id            = id            ?: 0L,
    code          = code          ?: "",
    fullName      = fullName      ?: "",
    age           = age           ?: 0,
    area          = area          ?: "",
    currentState  = currentState  ?: "",
    assignedNurse = assignedNurse ?: "",
    lastUpdate    = lastUpdate    ?: "",
    clinicalNotes = notes?.map { it.toDomain() } ?: emptyList()
)

fun PatientNoteDto.toDomain(): PatientNote = PatientNote(
    id      = id      ?: 0L,
    author  = author  ?: "",
    date    = date    ?: "",
    content = content ?: ""
)

fun AddNoteResponseDto.toDomain(): PatientNote = PatientNote(
    id      = id      ?: 0L,
    author  = author  ?: "",
    date    = date    ?: "",
    content = content ?: ""
)
