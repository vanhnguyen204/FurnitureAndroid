package com.example.furniture.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestInvoice
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.Invoice
import com.example.furniture.domain.repository.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private val invoiceRepository: InvoiceRepository) :
    ViewModel() {

    private val _invoices = MutableStateFlow<List<Invoice>>(emptyList())
    val invoices: StateFlow<List<Invoice>> get() = _invoices

    private val _invoiceDetails = MutableStateFlow<List<Cart>>(emptyList())
    val invoiceDetail: StateFlow<List<Cart>> get() = _invoiceDetails
    init {
        viewModelScope.launch {
            getInvoices()
        }
    }
    suspend fun createInvoice(
        requestInvoice: RequestInvoice
    ): Boolean {
        val sharedPreferences = invoiceRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "")

        val response = invoiceRepository.createInvoice(
            token = "Bear $getToken",
            requestInvoice
        )
        return response.status == 201
    }

    suspend fun getInvoices() {
        val sharedPreferences = invoiceRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "")
        val res = invoiceRepository.getMyInvoice("Bear $getToken")
        _invoices.emit(res)
    }
    suspend fun getInvoiceDetails(invoiceId: String) {
        val sharedPreferences = invoiceRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "")

        val res = invoiceRepository.getInvoiceDetails(invoiceId, "Bear $getToken")
        _invoiceDetails.emit(res)
    }
}