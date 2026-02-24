package com.TiololCode.medicgo.features.login.domain.usescases

import com.TiololCode.medicgo.features.login.domain.entities.LoginResult
import com.TiololCode.medicgo.features.login.domain.repositories.LoginRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(
        licenseNumber: String,
        password: String
    ): Result<LoginResult> {
        return try {
            if (licenseNumber.isBlank()) {
                return Result.failure(Exception("El número de licencia no puede estar vacío"))
            }
            if (password.isBlank()) {
                return Result.failure(Exception("La contraseña no puede estar vacía"))
            }
            loginRepository.login(licenseNumber.trim(), password)
        } catch (e: Exception) {
            android.util.Log.e("PostLoginUseCase", "Error en login: ${e.message}", e)
            Result.failure(e)
        }
    }
}
