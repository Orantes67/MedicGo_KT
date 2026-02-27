package com.TiololCode.medicgo.features.login.domain.repositories

import com.TiololCode.medicgo.features.login.domain.entities.LoginResult

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<LoginResult>
}
