package com.example.furniture.services

import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.Invoice
import com.example.furniture.data.model.response.MessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface InvoiceService {
    @POST("/api/invoice/v2/android")
    suspend fun createInvoice(
        @Header("Authorization") token: String,
        @Body requestInvoice: RequestInvoice
    ) : Response<MessageResponse>

    @GET("/api/invoice/")
    suspend fun getMyInvoice(
        @Header("Authorization") token: String,
    ): Response<List<Invoice>>

    @GET("/api/invoice/details/{invoiceId}")
    suspend fun getInvoiceDetails(
        @Header("Authorization") token: String,
        @Path("invoiceId") invoiceId: String
    ): Response<List<Cart>> // Cart is like a Product which includes quantity
}