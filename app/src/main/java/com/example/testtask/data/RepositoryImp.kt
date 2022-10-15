package com.example.testtask.data

import com.example.testtask.data.local_data_source.PizzaDao
import com.example.testtask.data.local_data_source.models.PizzaEntity
import com.example.testtask.data.remote_data_source.PizzaApi
import com.example.testtask.data.remote_data_source.models.PositionsResponse
import com.example.testtask.domain.Repository

class RepositoryImp(private val pizzaApi: PizzaApi, private val pizzaDao: PizzaDao): Repository {

    override suspend fun getPositions(): PositionsResponse{
        return pizzaApi.getPositions()
    }

    override suspend fun savePositions(positions: List<PizzaEntity>){
        pizzaDao.insertPositions(positions = positions)
    }

    override suspend fun getPositionsFromLocalDataSource(): List<PizzaEntity>{
        return pizzaDao.getPositions()
    }
}