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

    override suspend fun login(licenseNumber: String, password: String): Result<LoginResult> {
        return try {
            val response = loginApi.login(LoginRequest(licenseNumber, password))
            val domain = response.toDomain()
            if (domain.token.isBlank()) {
                Result.failure(Exception(domain.message.ifBlank { "Credenciales inválidas" }))
            } else {
                Result.success(domain)
            }
        } catch (e: HttpException) {
            val errorBody = try { e.response()?.errorBody()?.string() } catch (_: Exception) { null }
            val message = when (e.code()) {
                401 -> "Email o contraseña incorrectos"
                400 -> "Error 400: ${errorBody ?: "Datos inválidos"}"
                500 -> "Error en el servidor. Intenta más tarde"
                else -> "Error ${e.code()}: ${errorBody ?: "desconocido"}"
            }
            Result.failure(Exception(message))
        } catch (e: Exception) {
            Result.failure(Exception("Sin conexión o error inesperado"))
        }
    }
}
