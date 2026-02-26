package com.TiololCode.medicgo.features.register.domain.repositories

import com.TiololCode.medicgo.features.register.domain.entities.RegisterResult

interface RegisterRepository {
    suspend fun register(
        name: String,
        licenseNumber: String,
        specialty: String,
        email: String,
        password: String
    ): Result<RegisterResult>
}

