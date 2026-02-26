package com.TiololCode.medicgo.features.doctor.domain.usescases

import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import javax.inject.Inject

class GetDoctorMetricsUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: Long): Result<DoctorMetric> {
        return try {
            doctorRepository.getDoctorMetrics(doctorId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

