package com.TiololCode.medicgo.features.enfermero.data.repositories

import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.api.EnfermeroApi
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.mapper.toDomain
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.AddEnfermeroNotaRequestDto
import com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model.UpdateEnfermeroEstadoRequestDto
import com.TiololCode.medicgo.features.enfermero.domain.entities.MisPacientesResult
import com.TiololCode.medicgo.features.enfermero.domain.repositories.EnfermeroRepository
import javax.inject.Inject

class EnfermeroRepositoryImpl @Inject constructor(
    private val api: EnfermeroApi
) : EnfermeroRepository {

    override suspend fun getMisPacientes(): Result<MisPacientesResult> =
        runCatching { api.getMisPacientes().toDomain() }

    override suspend fun updatePacienteEstado(
        pacienteId: Long,
        nuevoEstado: String
    ): Result<String> = runCatching {
        val response = api.updatePacienteEstado(
            pacienteId = pacienteId,
            request = UpdateEnfermeroEstadoRequestDto(estado = nuevoEstado)
        )
        response.message ?: response.estado ?: nuevoEstado
    }

    override suspend fun addNotaRapida(
        pacienteId: Long,
        contenido: String
    ): Result<Unit> = runCatching {
        api.addNotaRapida(
            pacienteId = pacienteId,
            request = AddEnfermeroNotaRequestDto(contenido = contenido)
        )
        Unit
    }
}