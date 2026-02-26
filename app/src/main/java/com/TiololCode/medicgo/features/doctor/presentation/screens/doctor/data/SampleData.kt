package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.data

import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient

val sampleDoctorPatients = listOf(
    DoctorPatient(
        id = 1L,
        name = "Roberto",
        lastName = "Torres",
        age = 54,
        bloodType = "O+",
        symptoms = "Dolor en el pecho, dificultad para respirar, presión arterial elevada",
        currentState = "Crítico",
        conditionNote = "Requiere monitoreo constante",
        registrationDate = "2024-11-15",
        areaName = "Urgencias",
        assignedNurseName = "Ana García",
        assignedNurseId = 2L,
        lastUpdateTime = "2024-11-20 14:30",
        areaId = 1L
    ),
    DoctorPatient(
        id = 2L,
        name = "Laura",
        lastName = "Sánchez",
        age = 38,
        bloodType = "A-",
        symptoms = "Fiebre persistente, fatiga, dolor de cabeza moderado",
        currentState = "En observación",
        conditionNote = "Pendiente de resultados de laboratorio",
        registrationDate = "2024-11-14",
        areaName = "UCI",
        assignedNurseName = "Carlos Mendoza",
        assignedNurseId = 3L,
        lastUpdateTime = "2024-11-20 13:15",
        areaId = 2L
    ),
    DoctorPatient(
        id = 3L,
        name = "María",
        lastName = "González",
        age = 45,
        bloodType = "B+",
        symptoms = "Recuperación post-operatoria, sin complicaciones reportadas",
        currentState = "Estable",
        conditionNote = "Procedimiento exitoso, observar durante 48 horas",
        registrationDate = "2024-11-10",
        areaName = "Hospitalización",
        assignedNurseName = "Ana García",
        assignedNurseId = 2L,
        lastUpdateTime = "2024-11-20 10:45",
        areaId = 3L
    )
)

