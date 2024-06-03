package com.example.furniture.data.repository

import com.example.furniture.data.model.response.ErrorResponse
import com.example.furniture.data.model.response.User
import com.example.furniture.domain.repository.AuthRepository
import com.example.furniture.helper.Resource
import com.example.furniture.services.AuthService
import com.google.gson.Gson
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,

    ) : AuthRepository {


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
//    private val _user = MutableStateFlow<Resource<User>>(Resource.Error("Initial State"))
//    override val user: StateFlow<Resource<User>> get() = _user
//
//    override suspend fun authLogin(user: User): Resource<User> {
//        val response = authService.authLogin(user)
//        Log.e("VALUE", "authLogin: "  + response.body())
//         try {
//
//            if (response.isSuccessful && response.body() != null) {
//                _user.emit( Resource.Success(response.body()!!))
//              return  Resource.Success(response.body()!!)
//            } else {
//                val errorResponse = response.errorBody()?.string()?.let {
//                    Gson().fromJson(it, ErrorResponse::class.java)
//                }
//                _user.emit( Resource.ErrorRes(errorResponse ?: ErrorResponse(500, "Unknown", "Unknown error")))
//
//             return   Resource.ErrorRes(errorResponse ?: ErrorResponse(500, "Unknown", "Unknown error"))
//            }
//        } catch (e: Exception) {
//          return  Resource.Error(e.message ?: "An error occurred")
//        }
//    }
}
