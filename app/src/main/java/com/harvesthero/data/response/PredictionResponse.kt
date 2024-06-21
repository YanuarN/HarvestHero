package com.harvesthero.data.response

data class PredictionResponse(
    val status: String,
    val message: String,
    val data: PredictionData
)

data class PredictionData(
    val id: String,
    val result: String,
    val suggestion: List<String>,
    val createdAt: String
)