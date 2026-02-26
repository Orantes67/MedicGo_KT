package com.TiololCode.medicgo.features.doctor.data.datasource

import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote

object DoctorMockData {

    fun getMockMetrics(): DoctorMetric {
        return DoctorMetric(
            totalPatients = 8,
            patientsUnderObservation = 3,
            criticalPatients = 2,
            stablePatients = 3
        )
    }

    fun getMockPatients(): List<DoctorPatient> {
        return listOf(
            DoctorPatient(
                id = 1,
                name = "Juan",
                lastName = "Pérez",
                age = 45,
                areaId = 1,
                areaName = "UCI",
                currentState = "Crítico",
                conditionNote = "Paciente requiere monitoreo constante",
                bloodType = "O+",
                symptoms = "Fiebre, disnea, tos",
                registrationDate = "2024-02-20",
                assignedNurseId = 1,
                assignedNurseName = "María García",
                lastUpdateTime = "hace 2 horas"
            ),
            DoctorPatient(
                id = 2,
                name = "Carlos",
                lastName = "López",
                age = 38,
                areaId = 2,
                areaName = "Medicina General",
                currentState = "En observación",
                conditionNote = "Mejorando gradualmente",
                bloodType = "A+",
                symptoms = "Cefalea, mareos",
                registrationDate = "2024-02-18",
                assignedNurseId = 2,
                assignedNurseName = "Laura Martínez",
                lastUpdateTime = "hace 4 horas"
            ),
            DoctorPatient(
                id = 3,
                name = "Rosa",
                lastName = "Gutiérrez",
                age = 52,
                areaId = 1,
                areaName = "UCI",
                currentState = "Crítico",
                conditionNote = "Signos vitales inestables",
                bloodType = "B+",
                symptoms = "Hipoxia, taquicardia",
                registrationDate = "2024-02-19",
                assignedNurseId = 3,
                assignedNurseName = "Patricia Rodríguez",
                lastUpdateTime = "hace 1 hora"
            ),
            DoctorPatient(
                id = 4,
                name = "Miguel",
                lastName = "Sánchez",
                age = 41,
                areaId = 3,
                areaName = "Emergencia",
                currentState = "Estable",
                conditionNote = "Paciente estable, en recuperación",
                bloodType = "O-",
                symptoms = "Dolor abdominal",
                registrationDate = "2024-02-17",
                assignedNurseId = 1,
                assignedNurseName = "María García",
                lastUpdateTime = "hace 6 horas"
            ),
            DoctorPatient(
                id = 5,
                name = "Ana",
                lastName = "Moreno",
                age = 35,
                areaId = 2,
                areaName = "Medicina General",
                currentState = "Estable",
                conditionNote = "Sin complicaciones",
                bloodType = "AB+",
                symptoms = "Ninguno",
                registrationDate = "2024-02-16",
                assignedNurseId = 2,
                assignedNurseName = "Laura Martínez",
                lastUpdateTime = "hace 8 horas"
            ),
            DoctorPatient(
                id = 6,
                name = "Diego",
                lastName = "Fernández",
                age = 48,
                areaId = 3,
                areaName = "Emergencia",
                currentState = "En observación",
                conditionNote = "Pendiente de resultados de laboratorio",
                bloodType = "A-",
                symptoms = "Nauseas, vómito",
                registrationDate = "2024-02-21",
                assignedNurseId = 3,
                assignedNurseName = "Patricia Rodríguez",
                lastUpdateTime = "hace 3 horas"
            )
        )
    }

    fun getMockPatientNotes(): List<PatientNote> {
        return listOf(
            PatientNote(
                id = 1,
                patientId = 1,
                doctorId = 1,
                content = "Paciente presenta mejoría en saturación de oxígeno",
                createdDate = "2024-02-21 14:30"
            ),
            PatientNote(
                id = 2,
                patientId = 1,
                doctorId = 1,
                content = "Continuar con antibióticos IV",
                createdDate = "2024-02-20 10:15"
            ),
            PatientNote(
                id = 3,
                patientId = 1,
                doctorId = 1,
                content = "Paciente ingresa a UCI con signos críticos",
                createdDate = "2024-02-20 08:00"
            )
        )
    }
}

