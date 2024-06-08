package com.example.furniture.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestBodyShippingAddress
import com.example.furniture.data.model.request.ShippingAddressRequest
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.data.model.response.User
import com.example.furniture.domain.repository.ShippingAddressRepository
import com.example.furniture.helper.Console
import com.example.furniture.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ShippingAddressViewModel @Inject constructor(private val shippingAddressRepository: ShippingAddressRepository) :
    ViewModel() {
    private val _shippingAddresses = MutableStateFlow<List<ShippingAddress>>(emptyList())
    val shippingAddresses: StateFlow<List<ShippingAddress>>
        get() = _shippingAddresses
    private val _addresses = MutableStateFlow<List<String>>(emptyList())
    val addresses: StateFlow<List<String>> get() = _addresses

    init {
        viewModelScope.launch {
            getMyShippingAddress()

        }
    }

    suspend fun getMyShippingAddress() {
        val sharedPreferences = shippingAddressRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
        val response = shippingAddressRepository.getMyShippingAddress("Bear $getToken")
        _shippingAddresses.value = response

    }

    fun activeShippingAddress(id: String) {
        viewModelScope.launch {
            val sharedPreferences = shippingAddressRepository.getSharedPreferences()
            val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
            shippingAddressRepository.activeShippingAddress(
                "Bear $getToken",
                RequestBodyShippingAddress(id)
            )
            reloadShippingAddressAfterActive(id)
        }
    }

    private fun reloadShippingAddressAfterActive(id: String) {
        val updatedList = _shippingAddresses.value.map { item ->
            if (item.id == id) {
                item.copy(isSelected = true)
            } else {
                item.copy(isSelected = false)
            }
        }
        _shippingAddresses.value = updatedList
    }


    suspend fun getAddress(country: String) {
        try {
            val data = shippingAddressRepository.getAddress(RequestBodyShippingAddress(country))
            _addresses.emit(data)
        } catch (e: Exception) {
            Console().log("ERROR GET ADDRESS", e.message.toString())
        }
    }

    fun createShippingAddress(shippingAddress: ShippingAddressRequest) {
        val sharedPreferences = shippingAddressRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        viewModelScope.launch {
            shippingAddressRepository.createShippingAddress(
                token = "Bear $getToken",
                shippingAddress
            )
        }
    }

    suspend fun deleteShippingAddress(id: String): MessageResponse {
        val sharedPreferences = shippingAddressRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
        val response = shippingAddressRepository.deleteShippingAddress(
            token = "Bear $getToken",
            id
        )
        return response
    }

    suspend fun updateShippingAddress(shippingAddress: ShippingAddressRequest): MessageResponse {
        val sharedPreferences = shippingAddressRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
        val response =
            shippingAddressRepository.updateShippingAddress("Bear $getToken", shippingAddress)
        return response
    }

    fun getCurrentShippingAddress(): ShippingAddress {
        return _shippingAddresses.value.find { it.isSelected }
            ?: ShippingAddress(
                "1",
                "1",
                "Unknown",
                "Unknown",
                "Unknown",
                "Unknown",
                "Unknown",
                true
            )
    }
}