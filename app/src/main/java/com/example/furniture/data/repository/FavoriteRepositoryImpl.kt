package com.example.furniture.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.domain.repository.FavoriteRepository
import com.example.furniture.helper.Resource
import com.example.furniture.helper.getErrorResponse
import com.example.furniture.services.FavoriteService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteService: FavoriteService,
    private val sharedPreferences: SharedPreferences
) : FavoriteRepository {
    private val _favoriteProducts = MutableStateFlow<List<Product>>(emptyList())
    override val favoriteProducts: StateFlow<List<Product>>
        get() = _favoriteProducts

    override suspend fun getFavorites(token: String): Response<List<Product>> {
        val response = favoriteService.getFavorites(token)
        if (response.isSuccessful && response.body() != null) {
            _favoriteProducts.emit(response.body()!!)
        }
        return response
    }

    override fun getSharedPreferences(): SharedPreferences {
      return sharedPreferences
    }

    override suspend fun removeFavorite(
        token: String,
        requestBodyFavorite: RequestBodyFavorite
    ): Response<Product> {
        return favoriteService.removeFavorite(token, requestBodyFavorite)
    }
    override suspend fun checkIsLike(
        token: String,
        requestBodyFavorite: RequestBodyFavorite
    ): Resource<Product> {
        try {
            val response = favoriteService.checkIsLike(token, requestBodyFavorite)
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

    override suspend fun createFavorite(
        token: String,
        requestBodyFavorite: RequestBodyFavorite
    ): Response<Product> {
        return favoriteService.createFavorite(token, requestBodyFavorite)
    }

}