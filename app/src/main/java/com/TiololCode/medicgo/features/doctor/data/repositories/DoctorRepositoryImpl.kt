package com.TiololCode.medicgo.features.doctor.data.repositories

import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import com.TiololCode.medicgo.features.doctor.data.datasource.DoctorMockData
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor() : DoctorRepository {

    override suspend fun getDoctorMetrics(doctorId: Long): Result<DoctorMetric> {
        return try {
            val metrics = DoctorMockData.getMockMetrics()
            Result.success(metrics)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDoctorPatients(doctorId: Long): Result<List<DoctorPatient>> {
        return try {
            val patients = DoctorMockData.getMockPatients()
            Result.success(patients)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

