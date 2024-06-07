package com.example.furniture.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.furniture.data.model.request.RequestBodyPayment
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.Payment
import com.example.furniture.domain.repository.PaymentRepository
import com.example.furniture.helper.Console
import com.example.furniture.services.PaymentService
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val getSharedPreferences: SharedPreferences,
    private val paymentService: PaymentService
) : PaymentRepository {
    override fun getSharedPreferences(): SharedPreferences {
        return getSharedPreferences
    }

    override suspend fun getMyPayment(token: String): List<Payment> {
        try {
            val response = paymentService.getMyPayment(token)
            return response.body()!!
        } catch (e: Exception) {
            Log.e("ERROR GET PAYMENT", "getMyPayment: " + e.message)
            return emptyList()
        }
    }

    override suspend fun addPaymentMethod(
        token: String,
        payment: RequestBodyPayment
    ): MessageResponse {
        try {
            val response = paymentService.addNewPayment(token, payment)
            return response.body()!!
        } catch (e: Exception) {

            return MessageResponse("Error add payment: " + e.message, 500)
        }
    }

    override suspend fun activePayment(token: String, paymentId: String): MessageResponse {
        try {
            val response = paymentService.activePayment(token, paymentId)
            return response.body()!!
        } catch (e: Exception) {
            Console().log("ERROR ADD", "" + e.message)

            return MessageResponse("Error active payment: " + e.message, 500)
        }
    }

    override suspend fun removePayment(token: String, paymentId: String): MessageResponse {
        try {
            val response = paymentService.removePayment(token, paymentId)
            return response.body()!!
        } catch (e: Exception) {
            Console().log("ERRO REMOVE", "" + e.message)
            return MessageResponse("Error remove payment: " + e.message, 500)
        }
    }

}