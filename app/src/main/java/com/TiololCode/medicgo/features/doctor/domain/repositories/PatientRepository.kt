package com.TiololCode.medicgo.features.doctor.domain.repositories

import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote

interface PatientRepository {
    suspend fun updatePatientState(patientId: Long, newState: String): Result<String>
    suspend fun addPatientNote(patientId: Long, content: String): Result<PatientNote>
}

