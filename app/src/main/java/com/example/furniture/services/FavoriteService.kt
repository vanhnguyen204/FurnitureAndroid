package com.example.furniture.services

import com.example.furniture.data.model.response.Product
import com.example.furniture.data.model.request.RequestBodyFavorite
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
interface FavoriteService {


    @POST("/api/favorite/favorites-user/")
    suspend fun getFavorites(@Header("Authorization") token: String): Response<List<Product>>

    @POST("/api/favorite/create")
    suspend fun createFavorite(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBodyFavorite
    ): Response<Product>

    @POST("/api/favorite/delete")
    suspend fun removeFavorite(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBodyFavorite
    ): Response<Product>



    @POST("/api/favorite/isFavorite")
    suspend fun checkIsLike(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBodyFavorite
    ): Response<Product>
}