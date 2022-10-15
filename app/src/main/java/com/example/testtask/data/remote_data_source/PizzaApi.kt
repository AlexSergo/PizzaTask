package com.example.testtask.data.remote_data_source

import com.example.testtask.data.remote_data_source.models.PositionsResponse
import retrofit2.http.GET

interface PizzaApi {
    @GET("/APIpizza.json")
    suspend fun getPositions(): PositionsResponse
}