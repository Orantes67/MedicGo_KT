package com.TiololCode.medicgo.features.admistrator.data.repositories

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.AdminRemoteDataSource
import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.repositories.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: AdminRemoteDataSource
) : UsersRepository {

    override suspend fun getHealthProfessionals(): Result<List<HealthProfessional>> {
        return try {
            val result = remoteDataSource.getHealthProfessionals()
            result.map { (nurses, doctors) ->
                nurses + doctors
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}



