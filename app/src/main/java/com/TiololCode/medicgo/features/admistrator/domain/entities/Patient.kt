package com.TiololCode.medicgo.features.admistrator.domain.entities

data class Patient(
    val id: String,
    val name: String,           // nombre
    val lastName: String,       // apellido
    val bloodType: String,      // tipo_sangre
    val symptoms: String,       // sintomas (opcional)
    val currentState: String,   // estado_actual: Estable | Crítico | Observación
    val age: Int,               // edad
    val registrationDate: String, // fecha_registro
    val areaNombre: String,     // area_nombre: nombre del área
    val notaCondicion: String = "", // nota_condicion (opcional)
    val assignedDoctor: String = "",
    val assignedNurse: String = "",
    val nombreEnfermero: String = ""
)

