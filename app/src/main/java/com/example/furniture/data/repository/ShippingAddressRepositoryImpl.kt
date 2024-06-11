package com.example.furniture.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.furniture.data.model.request.RequestBodyShippingAddress
import com.example.furniture.data.model.request.ShippingAddressRequest
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.domain.repository.ShippingAddressRepository
import com.example.furniture.helper.Console
import com.example.furniture.helper.ConsoleLog
import com.example.furniture.services.ShippingAddressService

import javax.inject.Inject

class ShippingAddressRepositoryImpl @Inject constructor(
    private val shippingAddressService: ShippingAddressService,
    private val sharedPreferences: SharedPreferences,

    ) :
    ShippingAddressRepository {


    override fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    override suspend fun getMyShippingAddress(token: String): List<ShippingAddress> {
       try {
           val response = shippingAddressService.getMyShippingAddress(token)
           return response.body()!!
       }catch (e: Exception) {
           ConsoleLog("ERROR GET MY SHIPPING ADDRESS", e.message.toString())
           return emptyList()
       }
    }

    override suspend fun activeShippingAddress(
        token: String,
        shippingAddressId: RequestBodyShippingAddress
    ): Boolean {
        try {
            val response = shippingAddressService.activeShippingAddress(token, shippingAddressId)
            if (response.isSuccessful) {
                Log.e("ACTIVE", "activeShippingAddress: " + response.body())
                return true
            }
            return false
        } catch (e: Exception) {
            Console().log("ERROR ACTIVE", "" + e.message)
            return false
        }
    }

    override suspend fun getAddress(shippingAddressId: RequestBodyShippingAddress): List<String> {
        val response = shippingAddressService.getAddress(shippingAddressId);
        return response.body()!!
    }

    override suspend fun createShippingAddress(
        token: String,
        shippingAddress: ShippingAddressRequest
    ): MessageResponse {

        try {

            val response = shippingAddressService.createShippingAddress(
                token,
                shippingAddress
            )
            return response.body()!!
        } catch (e: Exception) {
            Log.e("ERROR", "createShippingAddress: " + e.message)
            return MessageResponse("ERROR CREATE SHIPPING ADDRESS", 400)
        }
    }

    override suspend fun deleteShippingAddress(
        token: String,
        id: String
    ): MessageResponse {
       try {
           val response = shippingAddressService.deleteShippingAddress(token = token, id)
           return response.body()!!
       }catch (e: Exception) {
           Log.e("ERROR", "Delete ShippingAddress: " + e.message)
           return MessageResponse("ERROR DELETE SHIPPING ADDRESS", 400)
       }
    }

    override suspend fun updateShippingAddress(
        token: String,
        shippingAddressRequest: ShippingAddressRequest
    ): MessageResponse {
        try {
            val response = shippingAddressService.updateShippingAddress(token = token, shippingAddressRequest)
            return response.body()!!
        }catch (e: Exception) {
            Log.e("ERROR", "Update ShippingAddress: " + e.message)
            return MessageResponse("ERROR UPDATE SHIPPING ADDRESS", 400)
        }
    }

}