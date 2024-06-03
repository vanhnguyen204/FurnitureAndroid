package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class Favorite(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("productId")
    val productId: String
)