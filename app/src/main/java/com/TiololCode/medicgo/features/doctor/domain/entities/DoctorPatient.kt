package com.TiololCode.medicgo.features.doctor.domain.entities

data class DoctorPatient(
    val id: Long,
    val name: String,
    val lastName: String,
    val age: Int,
    val areaId: Long,
    val areaName: String,
    val currentState: String,
    val conditionNote: String,
    val bloodType: String,
    val symptoms: String,
    val registrationDate: String,
    val assignedNurseId: Long,
    val assignedNurseName: String,
    val lastUpdateTime: String
)

