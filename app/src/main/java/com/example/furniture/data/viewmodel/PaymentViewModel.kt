package com.example.furniture.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestBodyPayment
import com.example.furniture.data.model.response.Payment
import com.example.furniture.domain.repository.PaymentRepository
import com.example.furniture.domain.repository.ShippingAddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val paymentRepository: PaymentRepository) :
    ViewModel() {
    private val _payments = MutableStateFlow<List<Payment>>(emptyList())
    val payments: StateFlow<List<Payment>> get() = _payments

    init {
        viewModelScope.launch {
            getMyPayment()
        }
    }

    suspend fun getMyPayment() {
        val sharedPreferences = paymentRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val response = paymentRepository.getMyPayment("Bear $getToken")
        _payments.emit(response)
    }

    suspend fun addNewPayment(payment: RequestBodyPayment): Boolean {
        val sharedPreferences = paymentRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
        val response = paymentRepository.addPaymentMethod("Bear $getToken", payment)

        return response.status == 201
    }

    suspend fun activePayment(paymentId: String) {

        val sharedPreferences = paymentRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = paymentRepository.activePayment("Bear $getToken", paymentId)

        if (res.status == 200) {
            val list: List<Payment> = _payments.value.map { payment: Payment ->
                if (payment.id == paymentId) {
                    payment.copy(isSelected = !payment.isSelected)
                } else {
                    payment.copy(isSelected = false)
                }
            }
            _payments.emit(list)
        }
    }
}