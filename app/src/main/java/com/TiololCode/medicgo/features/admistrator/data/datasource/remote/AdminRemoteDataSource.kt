package com.TiololCode.medicgo.features.admistrator.data.datasource.remote

import com.TiololCode.medicgo.core.di.AdministratorQualifier
import com.TiololCode.medicgo.core.security.TokenManager
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.api.AdminApi
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper.toArea
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper.toHealthProfessional
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper.toMetric
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AssignNurseRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.CreatePatientRequestDto
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import javax.inject.Inject

class AdminRemoteDataSource @Inject constructor(
    @AdministratorQualifier private val adminApi: AdminApi,
    private val tokenManager: TokenManager
) {

    suspend fun getMetrics(): Result<Metric> {
        return try {
            val response = adminApi.getMetrics()
            Result.success(response.toMetric())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getHealthProfessionals(): Result<Pair<List<HealthProfessional>, List<HealthProfessional>>> {
        return try {
            val response = adminApi.getHealthProfessionals()
            val nurses = response.nurses.map { it.toHealthProfessional() }
            val doctors = response.doctors.map { it.toHealthProfessional() }
            Result.success(Pair(nurses, doctors))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAreas(): Result<List<Area>> {
        return try {
            val response = adminApi.getAreas()
            val areas = response.map { it.toArea() }
            Result.success(areas)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addPatient(patient: Patient): Result<Patient> {
        return try {
            val request = CreatePatientRequestDto(
                name = patient.name,
                lastName = patient.lastName,
                bloodType = patient.bloodType,
                symptoms = patient.symptoms,
                currentState = patient.currentState,
                age = patient.age,
                areaId = patient.areaId,
                assignedDoctor = patient.assignedDoctor,
                assignedNurse = patient.assignedNurse
            )
            adminApi.createPatient(request)
            Result.success(patient)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun assignNurse(nurseId: Long, doctorId: Long): Result<Unit> {
        return try {
            val request = AssignNurseRequestDto(nurseId, doctorId)
            adminApi.assignNurse(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout(): Result<Unit> {
        return try {
            adminApi.logout()
            tokenManager.clearAllData()
            Result.success(Unit)
        } catch (e: Exception) {
            tokenManager.clearAllData()
            Result.failure(e)
        }
    }
}






