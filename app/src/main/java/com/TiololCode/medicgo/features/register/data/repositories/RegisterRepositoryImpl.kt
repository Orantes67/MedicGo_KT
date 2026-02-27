package com.TiololCode.medicgo.features.register.data.repositories

import com.TiololCode.medicgo.features.register.data.datasource.remote.api.RegisterApi
import com.TiololCode.medicgo.features.register.data.datasource.remote.mapper.toDomain
import com.TiololCode.medicgo.features.register.data.datasource.remote.model.RegisterRequestDto
import com.TiololCode.medicgo.features.register.domain.entities.RegisterResult
import com.TiololCode.medicgo.features.register.domain.repositories.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerApi: RegisterApi
) : RegisterRepository {

    override suspend fun register(
        name: String,
        licenseNumber: String,
        specialty: String,
        email: String,
        password: String,
        role: String
    ): Result<RegisterResult> {
        return try {
            val response = registerApi.register(
                RegisterRequestDto(
                    name = name,
                    licenseNumber = licenseNumber,
                    specialty = specialty,
                    email = email,
                    password = password,
                    role = role
                )
            )
            val domain = response.toDomain()
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

