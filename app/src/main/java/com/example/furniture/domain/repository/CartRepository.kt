package com.example.furniture.domain.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.helper.Resource
import com.example.furniture.services.RequestCartCreate

interface CartRepository {
    fun getSharedPreferences(): SharedPreferences
    suspend fun getMyCart(token: String): List<Cart>
    suspend fun addSingleProductToCart(
        token: String,
        requestCartCreate: RequestCartCreate
    ): MessageResponse

    suspend fun addAllToCart(token: String, productIds: List<String>): MessageResponse
}