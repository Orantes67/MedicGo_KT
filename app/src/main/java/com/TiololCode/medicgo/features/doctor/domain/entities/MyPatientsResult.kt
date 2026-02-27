package com.TiololCode.medicgo.features.doctor.domain.entities

/**
 * Resultado de GET /api/v1/doctores/mis-pacientes.
 * Contiene métricas y la lista completa de pacientes asignados.
 */
data class MyPatientsResult(
    val metrics: DoctorMetric,
    val patients: List<DoctorPatient>
) {
    val criticalPatients: List<DoctorPatient>
        get() = patients.filter { it.currentState.lowercase() == "critico" }
}
