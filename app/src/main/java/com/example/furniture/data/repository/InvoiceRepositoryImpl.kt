package com.example.furniture.data.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.Invoice
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.domain.repository.InvoiceRepository
import com.example.furniture.helper.ConsoleLog
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.services.InvoiceService
import javax.inject.Inject

class InvoiceRepositoryImpl @Inject constructor(
    private val invoiceService: InvoiceService,
    private val sharedPreferences: SharedPreferences
):InvoiceRepository {
    override fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    override suspend fun createInvoice(
        token: String,
        requestInvoice: RequestInvoice
    ): MessageResponse {
        try {
            val response = invoiceService.createInvoice(
                token,
                requestInvoice
            )
            return response.body()!!
        }catch (e: Exception) {
            ConsoleLog("ERROR CREATE INVOICE", e.message.toString())
            return MessageResponse("ERROR CREATE INVOICE", 500)
        }
    }

    override suspend fun getMyInvoice(token: String): List<Invoice> {
        try {
            val response = invoiceService.getMyInvoice(token)
            return response.body()!!
        }catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun getInvoiceDetails(invoiceId: String, token: String): List<Cart> {
        try {
            val response = invoiceService.getInvoiceDetails(token, invoiceId )
            return response.body()!!
        }catch (e: Exception) {
            return emptyList()
        }
    }

}