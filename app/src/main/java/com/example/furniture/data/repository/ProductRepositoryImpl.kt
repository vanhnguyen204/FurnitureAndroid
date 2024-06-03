package com.example.furniture.data.repository

import android.util.Log
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import com.example.furniture.domain.repository.ProductRepository
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
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    override val products: StateFlow<List<Product>>
        get() = _products

    override suspend fun getProducts(): Response<List<Product>> {
        val response = productService.getProducts()
        if (response.isSuccessful && response.body() != null) {
            _products.emit(response.body()!!)
        }else{
            Log.e("ERROR GET PRODUCTS", "getProducts: " + response.message() )
        }
        return response
    }

    override suspend fun productDetails(
        token: String,
        productId: RequestBodyFavorite
    ): Resource<Product> {
        try {
            val response = productService.productDetails(token, productId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body())

            }else{
                Resource.ErrorRes(getErrorResponse(response.errorBody().toString()))
            }
        }catch (e: Exception) {
            val error = e.message ?: "An error occurred"
            return  Resource.Error(error)
        }
    }

}