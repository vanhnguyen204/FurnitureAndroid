package com.example.furniture.data.model.request

data class ShippingAddressRequest (
   val shippingAddressId: String? = "",
   val country: String,
   val city: String,
   val district: String,
   val addressDetail: String,
   val recipient: String,
)