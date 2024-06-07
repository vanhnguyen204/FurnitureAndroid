package com.example.furniture.services

import com.example.furniture.data.model.request.RequestBodyPayment
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.Payment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentService {
    @GET("/api/payment/my-payment")
    suspend fun getMyPayment(@Header("Authorization") token: String): Response<List<Payment>>

    @POST("/api/payment/")
    suspend fun addNewPayment(
        @Header("Authorization") token: String,
        @Body payment: RequestBodyPayment
    ): Response<MessageResponse>

    @DELETE("/api/payment/{paymentId}")
    suspend fun removePayment(
        @Header("Authorization") token: String,
        @Path("paymentId") paymentId: String
    ): Response<MessageResponse>

    @POST("/api/payment/active/{paymentId}")
    suspend fun activePayment(
        @Header("Authorization") token: String,
        @Path("paymentId") paymentId: String
    ): Response<MessageResponse>
}