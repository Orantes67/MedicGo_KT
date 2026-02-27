package com.TiololCode.medicgo.features.admistrator.data.datasource.remote

import com.TiololCode.medicgo.core.di.AdministratorQualifier
import com.TiololCode.medicgo.core.security.TokenManager
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.api.AdminApi
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.api.PatientApi
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper.toArea
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper.toHealthProfessional
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper.toMetric
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AssignNurseRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AssignPatientRequestDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.CreatePatientRequestDto
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import javax.inject.Inject

class AdminRemoteDataSource @Inject constructor(
    @AdministratorQualifier private val adminApi: AdminApi,
    private val patientApi: PatientApi,
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

    /**
     * Paso 1 — POST /api/v1/pacientes  → crea el paciente y devuelve su id.
     * Paso 2 — PATCH /api/v1/pacientes/:id/asignar → asigna doctor y/o enfermero.
     */
    suspend fun addPatient(patient: Patient): Result<Patient> {
        return try {
            // ── Paso 1: crear paciente ──────────────────────────────────────
            val createRequest = CreatePatientRequestDto(
                nombre       = patient.name,
                apellido     = patient.lastName,
                edad         = patient.age,
                areaNombre   = patient.areaNombre,
                estadoActual = patient.currentState,
                tipoSangre   = patient.bloodType,
                fechaRegistro = patient.registrationDate,
                notaCondicion = patient.notaCondicion.ifBlank { null },
                sintomas      = patient.symptoms.ifBlank { null }
            )
            val created = patientApi.createPatient(createRequest)
            val patientId = created.id ?: created.mongoId
                ?: return Result.failure(Exception("El servidor no devolvió el ID del paciente"))

            // ── Paso 2: asignar doctor / enfermero (si se eligió alguno) ────
            val hasDoctor = patient.assignedDoctor.isNotBlank()
            val hasNurse  = patient.assignedNurse.isNotBlank()
            if (hasDoctor || hasNurse) {
                val assignRequest = AssignPatientRequestDto(
                    doctorId      = patient.assignedDoctor.ifBlank { null },
                    enfermeroId   = patient.assignedNurse.ifBlank { null },
                    nombreEnfermero = patient.nombreEnfermero.ifBlank { null }
                )
                patientApi.assignPatient(patientId, assignRequest)
            }

            Result.success(patient.copy(id = patientId))
        } catch (e: Exception) {
            android.util.Log.e("AdminRemoteDataSource", "Error adding patient: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun assignNurse(nurseId: String, doctorId: String): Result<Unit> {
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






