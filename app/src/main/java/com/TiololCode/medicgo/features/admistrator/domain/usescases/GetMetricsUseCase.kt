package com.TiololCode.medicgo.features.admistrator.domain.usescases

import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.repositories.MetricsRepository
import javax.inject.Inject

class GetMetricsUseCase @Inject constructor(
    private val metricsRepository: MetricsRepository
) {
    suspend operator fun invoke(): Result<Metric> {
        return try {
            metricsRepository.getMetrics()
        } catch (e: Exception) {
            android.util.Log.e("GetMetricsUseCase", "Error obtaining metrics: ${e.message}", e)
            Result.failure(e)
        }
    }
}

