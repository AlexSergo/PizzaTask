package com.example.testtask.domain.usecases

import com.example.testtask.data.local_data_source.models.PizzaEntity
import com.example.testtask.domain.Repository
import com.example.testtask.domain.models.Pizza

class SavePositionsUseCase(private val repository: Repository) {

    suspend fun execute(positions: List<Pizza>){
        var result = mutableListOf<PizzaEntity>()
        for (position in positions){
            result.add(
                PizzaEntity(
                    name = position.name,
                    title = position.title,
                    price = position.price,
                    urlPreview = position.urlPreview
                )
            )
        }
        repository.savePositions(result)
    }
}