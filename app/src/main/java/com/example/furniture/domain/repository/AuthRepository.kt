package com.example.furniture.domain.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.User
import com.example.furniture.helper.Resource
import com.example.furniture.services.UserInforRequest
import com.example.furniture.services.UserInforResponse

interface AuthRepository {
    fun getSharedPreferences(): SharedPreferences
    suspend fun authLogin(user: User) : Resource<User>
    suspend fun authRegister(user: User) : MessageResponse
    suspend fun getInforUser(token: String) : UserInforResponse
    suspend fun updateInforUser(token: String, userInforRequest: UserInforRequest) : MessageResponse
}