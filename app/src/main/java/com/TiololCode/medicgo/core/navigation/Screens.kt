package com.TiololCode.medicgo.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
object DoctorDashboard

@Serializable
data class PatientDetail(val patientId: Long)

