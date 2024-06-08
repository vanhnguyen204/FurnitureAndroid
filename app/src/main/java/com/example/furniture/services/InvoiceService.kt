package com.example.furniture.services

import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.MessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface InvoiceService {
    @POST("/api/invoice/v2/android")
    suspend fun createInvoice(
        @Header("Authorization") token: String,
        @Body requestInvoice: RequestInvoice
    ) : Response<MessageResponse>
}