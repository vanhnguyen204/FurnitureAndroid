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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    private val _myCart = MutableStateFlow<List<Cart>>(emptyList())
    val myCart: StateFlow<List<Cart>> get() = _myCart

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> get() = _totalPrice
    private var initialCartState: List<Cart> = emptyList()

    init {
        viewModelScope.launch {
            getMyCart()
        }
    }

    private  fun getToken(): String {
        val sharedPreferences = cartRepository.getSharedPreferences()
        return sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
    }

    private  fun calculateTotalPrice(carts: List<Cart>): Int {
        return carts.sumOf { it.quantity * it.price }
    }

    suspend fun getMyCart() {
        val token = getToken()
        val res = cartRepository.getMyCart("Bear $token")
        initialCartState = res
        _myCart.emit(res)
        _totalPrice.emit(calculateTotalPrice(res))
    }

    suspend fun addAllToCart(productIds: List<String>): Boolean {
        val token = getToken()
        val res = cartRepository.addAllToCart("Bear $token", productIds)
        return res.status == 200
    }

    suspend fun addSingleProductToCart(requestCartCreate: RequestCartCreate): Boolean {
        val token = getToken()
        val res = cartRepository.addSingleProductToCart("Bear $token", requestCartCreate)
        return res.status == 200 || res.status == 201
    }

    suspend fun handleIncreaseQuantity(productId: String) {
        updateCartQuantity(productId) { it + 1 }
    }

    suspend fun handleReduceQuantity(productId: String) {
        updateCartQuantity(productId) { it - 1 }
    }

    private suspend fun updateCartQuantity(productId: String, updateQuantity: (Int) -> Int) {
        _myCart.update { currentList ->
            currentList.map { cart ->
                if (cart.id == productId) {
                    cart.copy(quantity = updateQuantity(cart.quantity))
                } else {
                    cart
                }
            }
        }
        _totalPrice.emit(calculateTotalPrice(_myCart.value))
    }

    suspend fun resetCartToInitialState() {
        _myCart.emit(initialCartState)
        _totalPrice.emit(calculateTotalPrice(initialCartState))
    }

    suspend fun removeFromCart(productId: String) : Boolean {
        val token = getToken()
        val res = cartRepository.removeFromCart("Bear $token", productId)
        if (res.status == 200) {
            val updatedCart = _myCart.value.filter { it.id != productId }
            _myCart.emit(updatedCart)
            val updatedTotalPrice = updatedCart.sumOf { it.price * it.quantity }
            _totalPrice.emit(updatedTotalPrice)
        }
        return res.status == 200
    }
}
