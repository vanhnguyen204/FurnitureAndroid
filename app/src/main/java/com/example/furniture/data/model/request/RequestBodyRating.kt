package com.example.furniture.data.model.request

data class RequestBodyRating (
    val productId: String,
    val rate: Int,
    val comment: String
)