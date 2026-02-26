package com.TiololCode.medicgo.features.admistrator.data.datasource.remote.mapper

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.HealthProfessionalDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.MetricsResponseDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.PatientDto
import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.model.AreaResponseDto
import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area

fun MetricsResponseDto.toMetric(): Metric {
    return Metric(
        id = 1L,
        totalPatients = totalPatients,
        criticalPatients = criticalPatients,
        observationPatients = observationPatients
    )
}

fun HealthProfessionalDto.toHealthProfessional(): HealthProfessional {
    return HealthProfessional(
        id = id,
        name = name,
        profession = role,
        licenseNumber = licenseNumber
    )
}

fun PatientDto.toPatient(): Patient {
    return Patient(
        id = id,
        name = name,
        lastName = lastName,
        bloodType = bloodType,
        symptoms = symptoms,
        currentState = currentState,
        age = age,
        registrationDate = registrationDate,
        areaId = areaId,
        assignedDoctor = assignedDoctor,
        assignedNurse = assignedNurse
    )
}

fun AreaResponseDto.toArea(): Area {
    return Area(
        id = id,
        name = name,
        patientCount = totalPatients
    )
}


