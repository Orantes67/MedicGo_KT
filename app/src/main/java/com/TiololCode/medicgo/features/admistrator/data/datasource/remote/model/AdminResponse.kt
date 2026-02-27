package com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class MetricsResponseDto(
    @SerializedName("total_patients") val totalPatients: Int,
    @SerializedName("critical_patients") val criticalPatients: Int,
    @SerializedName("observation_patients") val observationPatients: Int
)

data class HealthProfessionalDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("license_number") val licenseNumber: String?,
    @SerializedName("rol") val role: String?,
    @SerializedName("especialidad") val specialty: String? = null
)

data class HealthProfessionalsResponseDto(
    @SerializedName("enfermeros") val nurses: List<HealthProfessionalDto>,
    @SerializedName("doctores") val doctors: List<HealthProfessionalDto>
)

data class PatientDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("bloodType") val bloodType: String?,
    @SerializedName("symptoms") val symptoms: String?,
    @SerializedName("currentState") val currentState: String?,
    @SerializedName("age") val age: Int?,
    @SerializedName("registrationDate") val registrationDate: String?,
    @SerializedName("areaId") val areaId: String?,
    @SerializedName("assignedDoctor") val assignedDoctor: String?,
    @SerializedName("assignedNurse") val assignedNurse: String?
)

data class AreaDistributionDto(
    @SerializedName("area_name") val areaName: String,
    @SerializedName("total_patients") val totalPatients: Int,
    @SerializedName("patients") val patients: List<PatientDto>
)

data class AreaResponseDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("area") val name: String?,
    @SerializedName("total") val totalPatients: Int?,
    @SerializedName("criticos") val criticos: Int? = 0,
    @SerializedName("estables") val estables: Int? = 0
)

data class CreateProfessionalRequestDto(
    @SerializedName("nombre") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("license_number") val licenseNumber: String,
    @SerializedName("password") val password: String,
    @SerializedName("rol") val role: String,
    @SerializedName("especialidad") val specialty: String? = null
)

data class AssignNurseRequestDto(
    @SerializedName("enfermero_id") val nurseId: String,
    @SerializedName("doctor_id") val doctorId: String
)

data class CreatePatientRequestDto(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("edad") val edad: Int,
    @SerializedName("area_nombre") val areaNombre: String,
    @SerializedName("estado_actual") val estadoActual: String,
    @SerializedName("tipo_sangre") val tipoSangre: String,
    @SerializedName("fecha_registro") val fechaRegistro: String,
    @SerializedName("nota_condicion") val notaCondicion: String? = null,
    @SerializedName("sintomas") val sintomas: String? = null
)

data class PatientCreatedResponseDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("_id") val mongoId: String? = null
)

data class AssignPatientRequestDto(
    @SerializedName("doctor_id") val doctorId: String? = null,
    @SerializedName("enfermero_id") val enfermeroId: String? = null,
    @SerializedName("nombre_enfermero") val nombreEnfermero: String? = null
)


