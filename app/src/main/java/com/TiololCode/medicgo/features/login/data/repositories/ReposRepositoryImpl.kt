package com.TiololCode.medicgo.features.login.data.repositories

import com.TiololCode.medicgo.features.login.data.datasource.remote.api.LoginApi
import com.TiololCode.medicgo.features.login.data.datasource.remote.mapper.toDomain
import com.TiololCode.medicgo.features.login.data.datasource.remote.model.LoginRequest
import com.TiololCode.medicgo.features.login.domain.entities.LoginResult
import com.TiololCode.medicgo.features.login.domain.repositories.LoginRepository
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginRepository {

    override suspend fun login(email: String, password: String): Result<LoginResult> {
        return try {
            val response = loginApi.login(LoginRequest(email, password))
            val domain = response.toDomain()
            if (domain.token.isBlank()) {
                Result.failure(Exception(domain.message.ifBlank { "Credenciales inválidas" }))
            } else {
                Result.success(domain)
            }
        } catch (e: HttpException) {
            val message = when (e.code()) {
                401 -> "Email o contraseña incorrectos"
                400 -> "Datos inválidos"
                500 -> "Error en el servidor. Intenta más tarde"
                else -> "Error al iniciar sesión (${e.code()})"
            }
            Result.failure(Exception(message))
        } catch (e: Exception) {
            Result.failure(Exception("Sin conexión o error inesperado"))
        }
    }
}
