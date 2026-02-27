package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.MyPatientsResult
import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import javax.inject.Inject

class GetMyPatientsUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(): Result<MyPatientsResult> =
        doctorRepository.getMyPatients()
}

