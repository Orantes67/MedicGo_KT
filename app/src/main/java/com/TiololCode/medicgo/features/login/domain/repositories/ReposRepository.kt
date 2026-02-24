package com.TiololCode.medicgo.features.login.domain.repositories

import com.TiololCode.medicgo.features.login.domain.entities.LoginResult

interface LoginRepository {
    suspend fun login(licenseNumber: String, password: String): Result<LoginResult>
}
