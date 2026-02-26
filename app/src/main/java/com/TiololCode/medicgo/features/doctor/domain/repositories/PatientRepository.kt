package com.TiololCode.medicgo.features.doctor.domain.repositories

import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote

interface PatientRepository {
    suspend fun getPatientNotes(patientId: Long): Result<List<PatientNote>>
    suspend fun addPatientNote(patientId: Long, content: String): Result<PatientNote>
    suspend fun updatePatientState(patientId: Long, newState: String): Result<Boolean>
}

