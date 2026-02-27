package com.TiololCode.medicgo.features.doctor.domain.entities

/**
 * Ficha completa de un paciente, incluyendo historial de notas clínicas.
 * Devuelta por GET /api/v1/doctores/pacientes/:id
 */
data class PatientDetail(
    val id: Long,
    val code: String,
    val fullName: String,
    val age: Int,
    val area: String,
    val currentState: String,    // "critico" | "observacion" | "estable"
    val assignedNurse: String,
    val lastUpdate: String,
    val clinicalNotes: List<PatientNote>
)
