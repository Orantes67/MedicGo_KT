package com.TiololCode.medicgo.features.admistrator.data.repositories

import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.repositories.MetricsRepository
import javax.inject.Inject

class MetricsRepositoryImpl @Inject constructor() : MetricsRepository {

    override suspend fun getMetrics(): Result<Metric> {
        return try {
            val metric = Metric(
                id = 1L,
                totalPatients = 150,
                criticalPatients = 5,
                observationPatients = 23
            )
            Result.success(metric)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

