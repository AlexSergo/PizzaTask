package com.example.testtask.data.remote_data_source.models

data class PositionResponse(
    val id: Int,
    val nameRu: String,
    val description: String,
    val price: Int,
    val urlPreview: String
)