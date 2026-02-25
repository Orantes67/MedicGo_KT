package com.TiololCode.medicgo.features.admistrator.data.repositories

import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import com.TiololCode.medicgo.features.admistrator.domain.repositories.AreasRepository
import javax.inject.Inject

class AreasRepositoryImpl @Inject constructor() : AreasRepository {

    override suspend fun getAreas(): Result<List<Area>> {
        return try {
            val areas = listOf(
                Area(1L, "Emergencia", 25),
                Area(2L, "Cuidados Intensivos", 15),
                Area(3L, "Pediatría", 40)
            )
            Result.success(areas)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addPatient(patient: Patient): Result<Patient> {
        return try {
            Result.success(patient)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

