package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import javax.inject.Inject

class AddPatientNoteUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patientId: Long, content: String): Result<PatientNote> {
        if (content.isBlank()) return Result.failure(IllegalArgumentException("La nota no puede estar vacía"))
        return patientRepository.addPatientNote(patientId, content)
    }
}

