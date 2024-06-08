package com.example.furniture.data.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.domain.repository.CartRepository
import com.example.furniture.helper.Console
import com.example.furniture.helper.ConsoleLog
import com.example.furniture.services.CartService
import com.example.furniture.services.RequestCartCreate
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartService: CartService,
    private val sharedPreferences: SharedPreferences
) : CartRepository {
    override fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    override suspend fun getMyCart(token: String): List<Cart> {
        try {
            val res = cartService.getMyCart(token)
            return res.body()!!

        } catch (e: Exception) {
            ConsoleLog("ERROR GET MY CAR", e.message.toString())
            return emptyList()
        }
    }

    override suspend fun addSingleProductToCart(
        token: String,
        requestCartCreate: RequestCartCreate
    ): MessageResponse {
      try {
          val res = cartService.addSingleProductToCart(token,requestCartCreate)
          return res.body()!!
      }catch (e: Exception) {
          return MessageResponse("ERROR ADD SINGLE PRODUCT TO CART", 400)
      }
    }

    override suspend fun addAllToCart(token: String, productIds: List<String>): MessageResponse {
        try {
            val response = cartService.addAllToCart(token, productIds);
            return response.body()!!
        }catch (e: Exception) {
            ConsoleLog("ERROR ADD ALL TO CART", e.message.toString())
            return MessageResponse("ERROR ADD ALL TO CART", 400)
        }
    }
}