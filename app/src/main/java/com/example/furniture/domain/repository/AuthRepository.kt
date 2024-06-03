package com.example.furniture.domain.repository

import com.example.furniture.data.model.response.User
import com.example.furniture.helper.Resource

interface AuthRepository {

    suspend fun authLogin(user: User) : Resource<User>
}