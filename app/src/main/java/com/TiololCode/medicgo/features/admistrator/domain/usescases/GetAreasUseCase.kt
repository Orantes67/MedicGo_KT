package com.TiololCode.medicgo.features.admistrator.domain.usescases

import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.repositories.AreasRepository
import javax.inject.Inject

class GetAreasUseCase @Inject constructor(
    private val areasRepository: AreasRepository
) {
    suspend operator fun invoke(): Result<List<Area>> {
        return try {
            areasRepository.getAreas()
        } catch (e: Exception) {
            android.util.Log.e("GetAreasUseCase", "Error obtaining areas: ${e.message}", e)
            Result.failure(e)
        }
    }
}

