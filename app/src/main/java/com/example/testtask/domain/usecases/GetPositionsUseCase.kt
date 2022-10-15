package com.example.testtask.domain.usecases

import com.example.testtask.domain.Repository
import com.example.testtask.domain.models.Pizza

class GetPositionsUseCase(private val repository: Repository) {

    suspend fun execute(): List<Pizza>{
        val positions = repository.getPositions()
        val result = mutableListOf<Pizza>()
        for (position in positions.pizza){
            result.add(
                Pizza(name = position.nameRu,
                    title = position.description,
                    price = position.price,
                    urlPreview = position.urlPreview)
            )
        }
        return result
    }
}