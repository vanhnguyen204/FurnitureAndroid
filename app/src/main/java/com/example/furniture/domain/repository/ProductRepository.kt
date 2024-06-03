package com.example.furniture.domain.repository

import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import com.example.furniture.helper.Resource
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface ProductRepository {
    val products: StateFlow<List<Product>>
    suspend fun getProducts(): Response<List<Product>>

    suspend fun productDetails(token: String, productId: RequestBodyFavorite): Resource<Product>
}