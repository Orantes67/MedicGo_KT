package com.TiololCode.medicgo.features.admistrator.domain.usescases

import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import com.TiololCode.medicgo.features.admistrator.domain.repositories.AreasRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(
    private val areasRepository: AreasRepository
) {
    suspend operator fun invoke(patient: Patient): Result<Patient> {
        return try {
            if (patient.name.isBlank()) {
                return Result.failure(Exception("El nombre del paciente no puede estar vacío"))
            }
            if (patient.lastName.isBlank()) {
                return Result.failure(Exception("Los apellidos del paciente no pueden estar vacíos"))
            }
            areasRepository.addPatient(patient)
        } catch (e: Exception) {
            android.util.Log.e("AddPatientUseCase", "Error adding patient: ${e.message}", e)
            Result.failure(e)
        }
    }
}

