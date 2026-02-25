package com.TiololCode.medicgo.features.admistrator.domain.entities

data class RecentActivity(
    val id: Long,
    val description: String,
    val timestamp: String,
    val activityType: String
)

