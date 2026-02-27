package com.TiololCode.medicgo.features.login.domain.usescases

import com.TiololCode.medicgo.features.login.domain.entities.LoginResult
import com.TiololCode.medicgo.features.login.domain.repositories.LoginRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<LoginResult> {
        return try {
            if (email.isBlank()) {
                return Result.failure(Exception("El email no puede estar vacío"))
            }
            if (password.isBlank()) {
                return Result.failure(Exception("La contraseña no puede estar vacía"))
            }
            loginRepository.login(email.trim(), password)
        } catch (e: Exception) {
            android.util.Log.e("PostLoginUseCase", "Error en login: ${e.message}", e)
            Result.failure(e)
        }
    }
}
