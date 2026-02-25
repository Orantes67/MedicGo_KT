package com.TiololCode.medicgo.features.admistrator.domain.entities

data class Metric(
    val id: Long,
    val totalPatients: Int,
    val criticalPatients: Int,
    val observationPatients: Int
)

data class RecentActivity(
    val id: Long,
    val description: String,
    val timestamp: String,
    val activityType: String
)

data class HealthProfessional(
    val id: Long,
    val name: String,
    val profession: String,
    val licenseNumber: String
)

data class Patient(
    val id: Long,
    val name: String,
    val lastName: String,
    val bloodType: String,
    val symptoms: String,
    val currentState: String,
    val age: Int,
    val registrationDate: String,
    val areaId: Long,
    val assignedDoctor: Long,
    val assignedNurse: Long
)

data class Area(
    val id: Long,
    val name: String,
    val patientCount: Int
)

