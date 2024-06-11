package com.example.furniture.domain.repository

import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import com.example.furniture.helper.Resource
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun productDetails(token: String, productId: RequestBodyFavorite)
            : Resource<Product>
    suspend fun getProductOfCategory(token: String, category: String)
    : List<Product>

    suspend fun searchProductByName(productName: String) : List<Product>
}