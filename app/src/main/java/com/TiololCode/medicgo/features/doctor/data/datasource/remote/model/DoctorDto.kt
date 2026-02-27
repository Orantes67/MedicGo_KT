package com.TiololCode.medicgo.features.doctor.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

// ─── GET /api/v1/doctores/mis-pacientes ───────────────────────────────────────

data class MyPatientsResponseDto(
    @SerializedName("stats")    val stats: StatsDto?,
    @SerializedName("patients") val patients: List<PatientItemDto>?
)

data class StatsDto(
    @SerializedName("total")       val total: Int?,
    @SerializedName("critico")     val critico: Int?,
    @SerializedName("observacion") val observacion: Int?,
    @SerializedName("estable")     val estable: Int?
)

data class PatientItemDto(
    @SerializedName("id")               val id: Long?,
    @SerializedName("code")             val code: String?,
    @SerializedName("full_name")        val fullName: String?,
    @SerializedName("age")              val age: Int?,
    @SerializedName("area")             val area: String?,
    @SerializedName("current_state")    val currentState: String?,
    @SerializedName("condition_note")   val conditionNote: String?,
    @SerializedName("assigned_nurse")   val assignedNurse: String?,
    @SerializedName("last_update")      val lastUpdate: String?,
    @SerializedName("next_appointment") val nextAppointment: String?
)

// ─── GET /api/v1/doctores/pacientes/{id} ──────────────────────────────────────

data class PatientDetailDto(
    @SerializedName("id")             val id: Long?,
    @SerializedName("code")           val code: String?,
    @SerializedName("full_name")      val fullName: String?,
    @SerializedName("age")            val age: Int?,
    @SerializedName("area")           val area: String?,
    @SerializedName("current_state")  val currentState: String?,
    @SerializedName("assigned_nurse") val assignedNurse: String?,
    @SerializedName("last_update")    val lastUpdate: String?,
    @SerializedName("notes")          val notes: List<PatientNoteDto>?
)

data class PatientNoteDto(
    @SerializedName("id")      val id: Long?,
    @SerializedName("author")  val author: String?,
    @SerializedName("date")    val date: String?,
    @SerializedName("content") val content: String?
)

// ─── PATCH /api/v1/doctores/pacientes/{id}/estado ─────────────────────────────

data class UpdateStateRequestDto(
    @SerializedName("estado") val estado: String
)

data class UpdateStateResponseDto(
    @SerializedName("estado")  val estado: String?,
    @SerializedName("message") val message: String?
)

// ─── POST /api/v1/doctores/pacientes/{id}/notas ───────────────────────────────

data class AddNoteRequestDto(
    @SerializedName("contenido") val contenido: String
)

data class AddNoteResponseDto(
    @SerializedName("id")      val id: Long?,
    @SerializedName("author")  val author: String?,
    @SerializedName("date")    val date: String?,
    @SerializedName("content") val content: String?
)
