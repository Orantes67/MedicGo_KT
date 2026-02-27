package com.TiololCode.medicgo.features.admistrator.domain.entities

data class Patient(
    val id: String,
    val name: String,
    val lastName: String,
    val bloodType: String,
    val symptoms: String,
    val currentState: String,
    val age: Int,
    val registrationDate: String,
    val areaId: String,
    val assignedDoctor: String,
    val assignedNurse: String
)

