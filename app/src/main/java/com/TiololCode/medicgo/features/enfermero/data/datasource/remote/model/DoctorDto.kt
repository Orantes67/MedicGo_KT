package com.TiololCode.medicgo.features.enfermero.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

// ─── GET /api/v1/enfermeros/mis-pacientes ─────────────────────────────────────

data class MisPacientesResponseDto(
    @SerializedName("stats")    val stats: EnfermeroStatsDto?,
    @SerializedName("patients") val patients: List<EnfermeroPatientItemDto>?
)

data class EnfermeroStatsDto(
    @SerializedName("total")       val total: Int?,
    @SerializedName("critico")     val critico: Int?,
    @SerializedName("observacion") val observacion: Int?,
    @SerializedName("estable")     val estable: Int?
)

data class EnfermeroPatientItemDto(
    @SerializedName("id")               val id: Long?,
    @SerializedName("code")             val code: String?,
    @SerializedName("full_name")        val fullName: String?,
    @SerializedName("age")              val age: Int?,
    @SerializedName("area")             val area: String?,
    @SerializedName("current_state")    val currentState: String?,
    @SerializedName("condition_note")   val conditionNote: String?,
    @SerializedName("last_update")      val lastUpdate: String?,
    @SerializedName("next_appointment") val nextAppointment: String?
)

// ─── PATCH /api/v1/enfermeros/pacientes/{id}/estado ───────────────────────────

data class UpdateEnfermeroEstadoRequestDto(
    @SerializedName("estado") val estado: String
)

data class UpdateEnfermeroEstadoResponseDto(
    @SerializedName("estado")  val estado: String?,
    @SerializedName("message") val message: String?
)

// ─── POST /api/v1/enfermeros/pacientes/{id}/notas ─────────────────────────────

data class AddEnfermeroNotaRequestDto(
    @SerializedName("contenido") val contenido: String
)

data class AddEnfermeroNotaResponseDto(
    @SerializedName("message") val message: String?
)
