package com.TiololCode.medicgo.features.enfermero.domain.repositories

import com.TiololCode.medicgo.features.enfermero.domain.entities.MisPacientesResult

interface EnfermeroRepository {
    suspend fun getMisPacientes(): Result<MisPacientesResult>
    suspend fun updatePacienteEstado(pacienteId: Long, nuevoEstado: String): Result<String>
    suspend fun addNotaRapida(pacienteId: Long, contenido: String): Result<Unit>
}
