package com.example.furniture.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.response.Cart
import com.example.furniture.domain.repository.CartRepository
import com.example.furniture.services.RequestCartCreate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    private val _myCart = MutableStateFlow<List<Cart>>(emptyList())
    val myCart: StateFlow<List<Cart>> get() = _myCart

    init {
        viewModelScope.launch {
            getMyCart()
        }
    }

    suspend fun getMyCart() {
        val sharedPreferences = cartRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = cartRepository.getMyCart("Bear $getToken")

        _myCart.emit(res)
    }

    suspend fun addAllToCart(productIds: List<String>): Boolean {
        val sharedPreferences = cartRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = cartRepository.addAllToCart("Bear $getToken", productIds)
        return res.status == 200
    }

    suspend fun addSingleProductToCart(requestCartCreate: RequestCartCreate): Boolean {
        val sharedPreferences = cartRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
        val res = cartRepository.addSingleProductToCart("Bear $getToken", requestCartCreate)
        return res.status == 200 || res.status == 201
    }
}