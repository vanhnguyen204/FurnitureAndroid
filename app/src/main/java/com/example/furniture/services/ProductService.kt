package com.example.furniture.services

import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductService{
    @GET("/api/product")
    suspend fun getProducts(): Response<List<Product>>
    @POST("/api/product/details")
    suspend fun productDetails(@Header("Authorization") token: String,@Body productId: RequestBodyFavorite): Response<Product>
}