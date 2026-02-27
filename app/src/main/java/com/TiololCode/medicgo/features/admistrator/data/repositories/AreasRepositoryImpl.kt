package com.TiololCode.medicgo.features.admistrator.data.repositories

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.AdminRemoteDataSource
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import com.TiololCode.medicgo.features.admistrator.domain.repositories.AreasRepository
import javax.inject.Inject

class AreasRepositoryImpl @Inject constructor(
    private val remoteDataSource: AdminRemoteDataSource
) : AreasRepository {

    override suspend fun getAreas(): Result<List<Area>> {
        return remoteDataSource.getAreas()
    }

    override suspend fun addPatient(patient: Patient): Result<Patient> {
        return remoteDataSource.addPatient(patient)
    }
}


