package com.example.furniture.domain.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.request.RequestBodyPayment
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.Payment

interface PaymentRepository {
    fun getSharedPreferences(): SharedPreferences
    suspend fun getMyPayment(token: String): List<Payment>
    suspend fun addPaymentMethod(token: String, payment: RequestBodyPayment): MessageResponse
    suspend fun activePayment(token: String, paymentId: String): MessageResponse
    suspend fun removePayment(token: String, paymentId: String): MessageResponse
}