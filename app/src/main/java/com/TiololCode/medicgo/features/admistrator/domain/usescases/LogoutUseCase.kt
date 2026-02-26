package com.TiololCode.medicgo.features.admistrator.domain.usescases

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.AdminRemoteDataSource
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val remoteDataSource: AdminRemoteDataSource
) {
    suspend operator fun invoke(): Result<Unit> {
        return try {
            remoteDataSource.logout()
        } catch (e: Exception) {
            android.util.Log.e("LogoutUseCase", "Error during logout: ${e.message}", e)
            Result.failure(e)
        }
    }
}

