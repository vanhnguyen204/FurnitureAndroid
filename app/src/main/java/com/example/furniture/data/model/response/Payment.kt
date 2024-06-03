package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class Payment(

    @SerializedName("_id")
    val id: String,
    val userId: String,
    val cartNumber: String,
    val expiryDate: String,
    val cvv: Int,
    val cartHolderName: String,
    val isSelected: Boolean,
    val type: String,
    val bankName: String,
    val image: String,
)