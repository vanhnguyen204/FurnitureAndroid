package com.example.furniture.data.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.response.ErrorResponse
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.User
import com.example.furniture.domain.repository.AuthRepository
import com.example.furniture.helper.ConsoleLog
import com.example.furniture.helper.Resource
import com.example.furniture.services.AuthService
import com.example.furniture.services.UserInforRequest
import com.example.furniture.services.UserInforResponse
import com.google.gson.Gson
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
private val sharedPreferences: SharedPreferences
    ) : AuthRepository {
    override fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }


    override suspend fun authLogin(user: User): Resource<User> {
        return try {
            val response = authService.authLogin(user)
            if (response.isSuccessful && response.body() != null) {
                val userResponse = response.body()!!
                Resource.Success(userResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                Resource.ErrorRes(errorResponse)
            }
        } catch (e: Exception) {
            println("ERROR " + e.message)
            val errorMessage = e.message ?: "An error occurred"
            Resource.Error(errorMessage)

        }
    }

    override suspend fun authRegister(user: User): MessageResponse {
        try {
            val res = authService.authRegister(user)
            return res.body()!!
        }catch (e: Exception) {
            ConsoleLog("ERROR REGISTER", e.message.toString())
            return MessageResponse("ERROR REGISTER", 500)
        }
    }

    override suspend fun getInforUser(token: String): UserInforResponse {
        try {
            val res = authService.getInforUser(token)
            return res.body()!!
        }catch (e: Exception) {
            return UserInforResponse("UnKnown", "UnKnown")
        }
    }

    override suspend fun updateInforUser(
        token: String,
        userInforRequest: UserInforRequest
    ): MessageResponse {
        try {
            val res = authService.updateInforUser(token, userInforRequest)
            return res.body()!!
        }catch (e: Exception) {
            return MessageResponse("ERROR UPDATE", 500)
        }
    }
}
