package com.TiololCode.medicgo.features.register.domain.usescases

import com.TiololCode.medicgo.features.register.domain.entities.RegisterResult
import com.TiololCode.medicgo.features.register.domain.repositories.RegisterRepository
import javax.inject.Inject

class PosRegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(
        name: String,
        licenseNumber: String,
        specialty: String,
        email: String,
        password: String,
        role: String
    ): Result<RegisterResult> {
        return try {
            if (name.isBlank()) {
                return Result.failure(Exception("El nombre no puede estar vacío"))
            }
            if (licenseNumber.isBlank()) {
                return Result.failure(Exception("El número de colegiado no puede estar vacío"))
            }
            if (specialty.isBlank()) {
                return Result.failure(Exception("Debe seleccionar una especialidad"))
            }
            if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return Result.failure(Exception("Ingresa un correo electrónico válido"))
            }
            if (password.length < 8) {
                return Result.failure(Exception("La contraseña debe tener al menos 8 caracteres"))
            }
            if (role.isBlank()) {
                return Result.failure(Exception("Debe seleccionar un rol"))
            }
            registerRepository.register(
                name = name.trim(),
                licenseNumber = licenseNumber.trim(),
                specialty = specialty.trim(),
                email = email.trim(),
                password = password,
                role = role.trim()
            )
        } catch (e: Exception) {
            android.util.Log.e("PosRegisterUseCase", "Error en registro: ${e.message}", e)
            Result.failure(e)
        }
    }
}

