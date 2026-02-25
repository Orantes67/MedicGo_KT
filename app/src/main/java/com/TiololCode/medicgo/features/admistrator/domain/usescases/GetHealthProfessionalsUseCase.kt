package com.TiololCode.medicgo.features.admistrator.domain.usescases

import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.repositories.UsersRepository
import javax.inject.Inject

class GetHealthProfessionalsUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Result<List<HealthProfessional>> {
        return try {
            usersRepository.getHealthProfessionals()
        } catch (e: Exception) {
            android.util.Log.e("GetHealthProfessionalsUseCase", "Error obtaining professionals: ${e.message}", e)
            Result.failure(e)
        }
    }
}

