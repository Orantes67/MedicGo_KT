package com.TiololCode.medicgo.features.login.data.repositories

import com.TiololCode.medicgo.features.login.data.datasource.remote.api.LoginApi
import com.TiololCode.medicgo.features.login.data.datasource.remote.mapper.toDomain
import com.TiololCode.medicgo.features.login.data.datasource.remote.model.LoginRequest
import com.TiololCode.medicgo.features.login.domain.entities.LoginResult
import com.TiololCode.medicgo.features.login.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginRepository {

    override suspend fun login(licenseNumber: String, password: String): Result<LoginResult> {
        return try {
            val response = loginApi.login(LoginRequest(licenseNumber, password))
            val domain = response.toDomain()
            if (domain.token.isBlank()) {
                Result.failure(Exception(domain.message.ifBlank { "Credenciales inválidas" }))
            } else {
                Result.success(domain)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
