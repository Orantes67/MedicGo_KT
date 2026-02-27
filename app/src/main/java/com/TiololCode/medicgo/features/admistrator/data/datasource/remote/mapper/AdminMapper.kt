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
        id = id.orEmpty(),
        name = name.orEmpty(),
        profession = role.orEmpty(),
        licenseNumber = licenseNumber.orEmpty()
    )
}

fun PatientDto.toPatient(): Patient {
    return Patient(
        id = id.orEmpty(),
        name = name.orEmpty(),
        lastName = lastName.orEmpty(),
        bloodType = bloodType.orEmpty(),
        symptoms = symptoms.orEmpty(),
        currentState = currentState.orEmpty(),
        age = age ?: 0,
        registrationDate = registrationDate.orEmpty(),
        areaId = areaId.orEmpty(),
        assignedDoctor = assignedDoctor.orEmpty(),
        assignedNurse = assignedNurse.orEmpty()
    )
}

fun AreaResponseDto.toArea(): Area {
    return Area(
        id = id.orEmpty(),
        name = name.orEmpty(),
        patientCount = totalPatients ?: 0,
        criticos = criticos ?: 0,
        estables = estables ?: 0
    )
}


