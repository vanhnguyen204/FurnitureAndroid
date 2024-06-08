package com.example.furniture.domain.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.MessageResponse
import retrofit2.Response
import retrofit2.http.Header

interface InvoiceRepository {
    fun getSharedPreferences(): SharedPreferences
    suspend fun createInvoice(
         token: String,
       requestInvoice: RequestInvoice
    ) : MessageResponse
}