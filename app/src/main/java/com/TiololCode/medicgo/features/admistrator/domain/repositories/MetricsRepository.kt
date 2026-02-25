package com.TiololCode.medicgo.features.admistrator.domain.repositories

import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.entities.RecentActivity

interface MetricsRepository {
    suspend fun getMetrics(): Result<Metric>
}

