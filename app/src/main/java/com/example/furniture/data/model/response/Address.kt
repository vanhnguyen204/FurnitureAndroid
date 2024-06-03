package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("_id")
    val id: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("district")
    val district: String,
)
