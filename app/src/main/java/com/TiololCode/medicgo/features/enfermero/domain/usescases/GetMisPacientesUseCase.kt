package com.TiololCode.medicgo.features.enfermero.domain.usescases

import com.TiololCode.medicgo.features.enfermero.domain.entities.MisPacientesResult
import com.TiololCode.medicgo.features.enfermero.domain.repositories.EnfermeroRepository
import javax.inject.Inject

class GetMisPacientesUseCase @Inject constructor(
    private val enfermeroRepository: EnfermeroRepository
) {
    suspend operator fun invoke(): Result<MisPacientesResult> =
        enfermeroRepository.getMisPacientes()
}
