package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Int?,
    @SerializedName("cause")
    val cause: String?,
    @SerializedName("message")
    val message: String?
)
