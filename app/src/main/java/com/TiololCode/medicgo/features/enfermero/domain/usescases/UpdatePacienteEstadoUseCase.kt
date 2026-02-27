package com.TiololCode.medicgo.features.enfermero.domain.usescases

import com.TiololCode.medicgo.features.enfermero.domain.repositories.EnfermeroRepository
import javax.inject.Inject

class UpdatePacienteEstadoUseCase @Inject constructor(
    private val enfermeroRepository: EnfermeroRepository
) {
    suspend operator fun invoke(pacienteId: Long, nuevoEstado: String): Result<String> =
        enfermeroRepository.updatePacienteEstado(pacienteId, nuevoEstado)
}
