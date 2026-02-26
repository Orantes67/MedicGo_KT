package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import javax.inject.Inject

class GetPatientNotesUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patientId: Long): Result<List<PatientNote>> {
        return try {
            patientRepository.getPatientNotes(patientId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

