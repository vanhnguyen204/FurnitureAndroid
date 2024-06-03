package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class MessageResponse (
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    )