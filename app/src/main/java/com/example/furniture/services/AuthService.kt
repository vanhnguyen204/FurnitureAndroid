package com.example.furniture.services

import com.example.furniture.data.model.response.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/login")
    suspend fun authLogin(@Body user: User): Response<User>
}