package com.TiololCode.medicgo.features.doctor.data.repositories

import com.TiololCode.medicgo.features.doctor.data.datasource.remote.api.DoctorApi
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.mapper.toDomain
import com.TiololCode.medicgo.features.doctor.domain.entities.MyPatientsResult
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientDetail
import com.TiololCode.medicgo.features.doctor.domain.repositories.DoctorRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorApi: DoctorApi
) : DoctorRepository {

    override suspend fun getMyPatients(): Result<MyPatientsResult> = runCatching {
        doctorApi.getMyPatients().toDomain()
    }

    override suspend fun getPatientDetail(patientId: Long): Result<PatientDetail> = runCatching {
        doctorApi.getPatientDetail(patientId).toDomain()
    }
}

