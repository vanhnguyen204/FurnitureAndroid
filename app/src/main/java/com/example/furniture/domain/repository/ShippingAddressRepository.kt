package com.example.furniture.domain.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.request.RequestBodyShippingAddress
import com.example.furniture.data.model.request.ShippingAddressRequest
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.model.response.ShippingAddress
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response


interface ShippingAddressRepository {
    fun getSharedPreferences(): SharedPreferences
    suspend fun getMyShippingAddress(token: String): List<ShippingAddress>

    suspend fun activeShippingAddress(
        token: String,
        shippingAddressId: RequestBodyShippingAddress
    ): Boolean

    suspend fun getAddress(shippingAddressId: RequestBodyShippingAddress): List<String>

    suspend fun createShippingAddress(
        token: String,
        shippingAddress: ShippingAddressRequest
    ): MessageResponse

    suspend fun deleteShippingAddress(token: String,id: String): MessageResponse
    suspend fun updateShippingAddress(token: String,shippingAddressRequest: ShippingAddressRequest): MessageResponse
}