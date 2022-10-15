package com.example.testtask.domain

import com.example.testtask.data.local_data_source.models.PizzaEntity
import com.example.testtask.data.remote_data_source.models.PositionsResponse

interface Repository {
    suspend fun getPositions(): PositionsResponse
    suspend fun savePositions(positions: List<PizzaEntity>)
    suspend fun getPositionsFromLocalDataSource(): List<PizzaEntity>
}