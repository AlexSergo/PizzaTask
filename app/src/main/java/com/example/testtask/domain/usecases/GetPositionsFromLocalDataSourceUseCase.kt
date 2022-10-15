package com.example.testtask.domain.usecases

import com.example.testtask.domain.Repository
import com.example.testtask.domain.models.Pizza

class GetPositionsFromLocalDataSourceUseCase(private val repository: Repository) {

    suspend fun execute(): List<Pizza>{
        val positions = repository.getPositionsFromLocalDataSource()
        val result = mutableListOf<Pizza>()
        for (position in positions){
            result.add(
                Pizza(
                    name = position.name,
                    title = position.title,
                    price = position.price,
                    urlPreview = position.urlPreview
                )
            )
        }
        return result
    }
}