package com.TiololCode.medicgo.features.doctor.domain.repositories

import com.TiololCode.medicgo.features.doctor.domain.entities.MyPatientsResult
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientDetail

interface DoctorRepository {
    suspend fun getMyPatients(): Result<MyPatientsResult>
    suspend fun getPatientDetail(patientId: Long): Result<PatientDetail>
}

