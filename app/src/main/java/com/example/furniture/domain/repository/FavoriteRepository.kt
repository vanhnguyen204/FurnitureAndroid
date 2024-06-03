package com.example.furniture.domain.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.helper.Resource
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface FavoriteRepository {
    val favoriteProducts: StateFlow<List<Product>>
    suspend fun getFavorites(token: String): Response<List<Product>>
    fun getSharedPreferences(): SharedPreferences
    suspend fun removeFavorite(token: String, requestBodyFavorite: RequestBodyFavorite): Response<Product>

    suspend fun checkIsLike(token: String, requestBodyFavorite: RequestBodyFavorite): Resource<Product>
    suspend fun createFavorite(token: String, requestBodyFavorite: RequestBodyFavorite): Response<Product>
}