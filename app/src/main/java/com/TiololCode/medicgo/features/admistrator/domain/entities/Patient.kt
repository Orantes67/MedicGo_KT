package com.TiololCode.medicgo.features.admistrator.domain.entities

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

