package com.example.furniture.services

import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

data class UserInforResponse(
    val name: String,
    val email: String,
)

data class UserInforRequest(
    val name: String,
    val password: String
)

interface AuthService {
    @POST("/api/auth/login")
    suspend fun authLogin(@Body user: User): Response<User>

    @POST("/api/auth/register")
    suspend fun authRegister(@Body user: User): Response<MessageResponse>

    @GET("/api/auth/infor")
    suspend fun getInforUser(@Header("Authorization") token: String): Response<UserInforResponse>

    @PATCH("/api/auth/user")
    suspend fun updateInforUser(
        @Header("Authorization") token: String,
        @Body userInforRequest: UserInforRequest
    ): Response<MessageResponse>
}