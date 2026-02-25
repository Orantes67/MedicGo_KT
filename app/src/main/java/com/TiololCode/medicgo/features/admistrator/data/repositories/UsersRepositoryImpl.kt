package com.TiololCode.medicgo.features.admistrator.data.repositories

import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.repositories.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor() : UsersRepository {

    override suspend fun getHealthProfessionals(): Result<List<HealthProfessional>> {
        return try {
            val professionals = listOf(
                HealthProfessional(1L, "Dr. Juan Pérez", "Médico", "LIC123456"),
                HealthProfessional(2L, "Enfermera María García", "Enfermera", "LIC789012"),
                HealthProfessional(3L, "Dr. Carlos López", "Médico", "LIC345678")
            )
            Result.success(professionals)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

