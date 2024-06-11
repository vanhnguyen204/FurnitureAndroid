package com.example.furniture.data.repository

import android.util.Log
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import com.example.furniture.domain.repository.ProductRepository
import com.example.furniture.helper.ConsoleLog
import com.example.furniture.helper.Resource
import com.example.furniture.helper.getErrorResponse
import com.example.furniture.services.ProductService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,

    ) : ProductRepository {


    override suspend fun getProducts():List<Product>{
        try {
            val res = productService.getProducts()
            return res.body()!!
        } catch (e: Exception) {
            ConsoleLog("ERROR GET PRODUCT OF CATEGORY", e.message.toString())
            return emptyList()
        }
    }

    override suspend fun productDetails(
        token: String,
        productId: RequestBodyFavorite
    ): Resource<Product> {
        try {
            val response = productService.productDetails(token, productId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body())

            } else {
                Resource.ErrorRes(getErrorResponse(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            val error = e.message ?: "An error occurred"
            return Resource.Error(error)
        }
    }

    override suspend fun getProductOfCategory(
        token: String,
        category: String
    ): List<Product> {
        try {
            val res = productService.getProductOfCategory(token, category)
            return res.body()!!
        } catch (e: Exception) {
            ConsoleLog("ERROR GET PRODUCT OF CATEGORY", e.message.toString())
            return emptyList()
        }
    }

    override suspend fun searchProductByName(productName: String): List<Product> {
        try {
            val res = productService.searchProductByName(productName)
            return res.body()!!
        }catch (e: Exception) {
            return emptyList()
        }
    }

}