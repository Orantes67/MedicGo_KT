package com.TiololCode.medicgo.features.admistrator.domain.repositories

import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient

interface AreasRepository {
    suspend fun getAreas(): Result<List<Area>>

    suspend fun addPatient(patient: Patient): Result<Patient>
}

