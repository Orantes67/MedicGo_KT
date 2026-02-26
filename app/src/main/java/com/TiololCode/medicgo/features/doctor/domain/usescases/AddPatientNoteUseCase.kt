package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import javax.inject.Inject

class AddPatientNoteUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patientId: Long, content: String): Result<PatientNote> {
        return try {
            patientRepository.addPatientNote(patientId, content)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

