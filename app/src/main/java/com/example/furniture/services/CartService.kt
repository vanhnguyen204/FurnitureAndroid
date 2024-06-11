package com.example.furniture.services

import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.MessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

data class RequestCartCreate(
    val productId: String,
    val quantity: Int
)
interface CartService {
    @GET("/api/cart/my-cart")
    suspend fun getMyCart(@Header("Authorization") token: String): Response<List<Cart>>

    @POST("/api/cart/add")
    suspend fun addSingleProductToCart(@Header("Authorization") token: String, @Body requestCartCreate: RequestCartCreate): Response<MessageResponse>

    @POST("/api/cart/add-all")
    suspend fun addAllToCart(@Header("Authorization") token: String, @Body productIds: List<String>): Response<MessageResponse>

@DELETE("/api/cart/{productId}")
suspend fun removeFromCart(
    @Header("Authorization") token: String,
    @Path("productId") productId: String
): Response<MessageResponse>
}