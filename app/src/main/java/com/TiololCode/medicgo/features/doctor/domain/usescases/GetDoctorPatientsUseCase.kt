package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import javax.inject.Inject

class GetDoctorPatientsUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: Long): Result<List<DoctorPatient>> {
        return try {
            doctorRepository.getDoctorPatients(doctorId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

