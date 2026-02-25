package com.TiololCode.medicgo.features.admistrator.domain.repositories

import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional

interface UsersRepository {
    suspend fun getHealthProfessionals(): Result<List<HealthProfessional>>
}

