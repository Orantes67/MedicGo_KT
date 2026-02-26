package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import javax.inject.Inject

class UpdatePatientStateUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patientId: Long, newState: String): Result<Boolean> {
        return try {
            patientRepository.updatePatientState(patientId, newState)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

