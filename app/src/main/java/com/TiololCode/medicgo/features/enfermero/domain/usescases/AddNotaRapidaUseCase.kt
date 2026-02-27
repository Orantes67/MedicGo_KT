package com.TiololCode.medicgo.features.enfermero.domain.usescases

import com.TiololCode.medicgo.features.enfermero.domain.repositories.EnfermeroRepository
import javax.inject.Inject

class AddNotaRapidaUseCase @Inject constructor(
    private val enfermeroRepository: EnfermeroRepository
) {
    suspend operator fun invoke(pacienteId: Long, contenido: String): Result<Unit> {
        if (contenido.isBlank()) return Result.failure(IllegalArgumentException("La nota no puede estar vacía"))
        return enfermeroRepository.addNotaRapida(pacienteId, contenido)
    }
}
