package com.TiololCode.medicgo.features.doctor.data.repositories

import com.TiololCode.medicgo.features.doctor.data.datasource.remote.api.DoctorApi
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.mapper.toDomain
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.AddNoteRequestDto
import com.TiololCode.medicgo.features.doctor.data.datasource.remote.model.UpdateStateRequestDto
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.domain.repositories.PatientRepository
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val doctorApi: DoctorApi
) : PatientRepository {

    override suspend fun updatePatientState(patientId: Long, newState: String): Result<String> =
        runCatching {
            val response = doctorApi.updatePatientState(
                patientId,
                UpdateStateRequestDto(estado = newState)
            )
            response.estado ?: newState
        }

    override suspend fun addPatientNote(patientId: Long, content: String): Result<PatientNote> =
        runCatching {
            doctorApi.addPatientNote(
                patientId,
                AddNoteRequestDto(contenido = content)
            ).toDomain()
        }
}


