package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName
data class Cart(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("quantity")
    val quantity: Int
)

