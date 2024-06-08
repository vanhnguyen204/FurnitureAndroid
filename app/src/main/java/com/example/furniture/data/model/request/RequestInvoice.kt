package com.example.furniture.data.model.request

import com.example.furniture.data.model.response.Cart
import com.google.gson.annotations.SerializedName

data class RequestInvoice(
   val totalPrice: Int,
   val paymentType: String,
   val shippingAddress: String,
   val delivery: String,
   @SerializedName("data")
   val data: List<Cart>
)