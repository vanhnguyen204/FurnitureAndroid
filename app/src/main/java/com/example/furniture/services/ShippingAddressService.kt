package com.example.furniture.services

import com.example.furniture.data.model.request.RequestBodyShippingAddress
import com.example.furniture.data.model.request.ShippingAddressRequest
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.ShippingAddress
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ShippingAddressService {
    @GET("/api/shipping-address/")
    suspend fun getMyShippingAddress(@Header("Authorization") token: String): Response<List<ShippingAddress>>

    @POST("/api/shipping-address/")
    suspend fun createShippingAddress(
        @Header("Authorization") token: String,
        @Body shippingAddress: ShippingAddressRequest
    ): Response<MessageResponse>

    @PATCH("/api/shipping-address/")
    suspend fun updateShippingAddress(
        @Header("Authorization") token: String,
        @Body shippingAddressRequest: ShippingAddressRequest
    ): Response<MessageResponse>

    @DELETE("/api/shipping-address/")
    suspend fun deleteShippingAddress(
        @Header("Authorization") token: String,
        @Query("id") shippingAddressId: String
    ): Response<MessageResponse>

    @POST("/api/shipping-address/active")
    suspend fun activeShippingAddress(
        @Header("Authorization") token: String,
        @Body shippingAddressId: RequestBodyShippingAddress
    ): Response<MessageResponse>

    @POST("/api/address/")
    suspend fun getAddress(@Body shippingAddressId: RequestBodyShippingAddress): Response<List<String>>
}