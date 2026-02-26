package com.TiololCode.medicgo.features.doctor.domain.repositories

import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric

interface DoctorRepository {
    suspend fun getDoctorMetrics(doctorId: Long): Result<DoctorMetric>
    suspend fun getDoctorPatients(doctorId: Long): Result<List<DoctorPatient>>
}

