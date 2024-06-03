package com.example.furniture.helper

import com.example.furniture.data.model.response.ErrorResponse
import com.google.gson.Gson

fun getErrorResponse(response:String): ErrorResponse {
    return Gson().fromJson(response, ErrorResponse::class.java)

}