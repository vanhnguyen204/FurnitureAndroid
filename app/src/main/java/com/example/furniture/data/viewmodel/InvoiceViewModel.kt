package com.example.furniture.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.domain.repository.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private val invoiceRepository: InvoiceRepository) :
    ViewModel() {

    suspend fun createInvoice(
       requestInvoice: RequestInvoice
    ):Boolean {
        val sharedPreferences = invoiceRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "")

      val response =   invoiceRepository.createInvoice(
            token = "Bear $getToken",
            requestInvoice
        )
        return response.status == 201
    }
}