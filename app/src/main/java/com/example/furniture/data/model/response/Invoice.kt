package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class Invoice(
    @SerializedName("_id")
    val id: String,
    val userId: String,
    val totalPrice: Int,
    val dateExport: String,
    val paymentType: String,
    val shippingAddress: String,
    val delivery: String,
    )