package com.example.furniture.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import com.example.furniture.domain.repository.ProductRepository
import com.example.furniture.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _productsSearch = MutableStateFlow<List<Product>>(emptyList())
    val productsSearch : StateFlow<List<Product>> get() = _productsSearch
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _productDetails = MutableLiveData<Resource<Product>>()
    val productDetails: LiveData<Resource<Product>> get() = _productDetails

    init {
        viewModelScope.launch {
            getProducts()
        }
    }

    suspend fun getProducts() {
        val res = productRepository.getProducts()
        _products.emit(res)
    }

    suspend fun productDetails(token: String, productId: RequestBodyFavorite) {
        val response = productRepository.productDetails(token, productId)
        _productDetails.postValue(response)
    }

    suspend fun getProductOfCategory(token: String, category: String) {
        val res = productRepository.getProductOfCategory(token, category)
        _products.emit(res)
    }
    suspend fun searchProductByName(productName: String) {
        val res = productRepository.searchProductByName(productName)
        _productsSearch.emit(res)
    }
}
