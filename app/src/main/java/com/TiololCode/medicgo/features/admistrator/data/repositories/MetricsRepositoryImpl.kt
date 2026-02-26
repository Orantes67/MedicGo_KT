package com.TiololCode.medicgo.features.admistrator.data.repositories

import com.TiololCode.medicgo.features.admistrator.data.datasource.remote.AdminRemoteDataSource
import com.TiololCode.medicgo.features.admistrator.domain.entities.Metric
import com.TiololCode.medicgo.features.admistrator.domain.repositories.MetricsRepository
import javax.inject.Inject

class MetricsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AdminRemoteDataSource
) : MetricsRepository {

    override suspend fun getMetrics(): Result<Metric> {
        return remoteDataSource.getMetrics()
    }
}


