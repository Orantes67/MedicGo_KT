package com.TiololCode.medicgo.features.doctor.data.repositories

import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.data.datasource.DoctorMockData
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor() : PatientRepository {

    override suspend fun getPatientNotes(patientId: Long): Result<List<PatientNote>> {
        return try {
            val notes = DoctorMockData.getMockPatientNotes()
            Result.success(notes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addPatientNote(patientId: Long, content: String): Result<PatientNote> {
        return try {
            val newNote = PatientNote(
                id = System.currentTimeMillis(),
                patientId = patientId,
                doctorId = 1,
                content = content,
                createdDate = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(java.util.Date())
            )
            Result.success(newNote)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePatientState(patientId: Long, newState: String): Result<Boolean> {
        return try {
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

