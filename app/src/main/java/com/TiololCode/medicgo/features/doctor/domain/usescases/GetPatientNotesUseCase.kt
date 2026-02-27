package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.PatientDetail
import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import javax.inject.Inject

class GetPatientDetailUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(patientId: Long): Result<PatientDetail> =
        doctorRepository.getPatientDetail(patientId)
}

