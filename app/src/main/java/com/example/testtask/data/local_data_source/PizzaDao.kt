package com.example.testtask.data.local_data_source

import androidx.room.*
import com.example.testtask.data.local_data_source.models.PizzaEntity

@Dao
interface PizzaDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPositions(positions: List<PizzaEntity>)
    @Update
    suspend fun updatePosition(pizzaEntity: PizzaEntity)
    @Query("SELECT * FROM pizza")
    suspend fun getPositions(): List<PizzaEntity>
}