package com.example.furniture.data.model.request

data class RequestBodyPayment(
   val cartNumber: String,
   val bankName: String,
   val expiryDate: String,
   val cvv: String,
   val cartHolderName: String,
   val type: String,
)