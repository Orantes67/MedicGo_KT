package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import javax.inject.Inject

class UpdatePatientStateUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patientId: Long, newState: String): Result<String> =
        patientRepository.updatePatientState(patientId, newState)
}

