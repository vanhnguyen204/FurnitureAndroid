package com.example.furniture.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.response.User
import com.example.furniture.domain.repository.AuthRepository
import com.example.furniture.helper.Resource
import com.example.furniture.services.UserInforRequest
import com.example.furniture.services.UserInforResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>> get() = _user

    private val _userInfor = MutableStateFlow<UserInforResponse>(UserInforResponse("", ""))
    val userInfor: StateFlow<UserInforResponse> get() = _userInfor


    private fun getToken(): String {
        val sharedPreferences = authRepository.getSharedPreferences()
        return sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""
    }

    fun authLogin(user: User) {
        viewModelScope.launch {
            val resource = authRepository.authLogin(user)
            _user.value = resource
        }
    }

    suspend fun authRegister(user: User): Boolean {
        val res = authRepository.authRegister(user)
        return res.status == 201
    }

    init {
        viewModelScope.launch {
            getUserInfor()
        }
    }

    suspend fun getUserInfor() {
        val token = getToken()
        val res = authRepository.getInforUser("Bear $token")
        _userInfor.emit(res)
    }

    suspend fun updateInfor(userInforRequest: UserInforRequest) {
        val token = getToken()
        val temp = _userInfor.value.copy(name = userInforRequest.name)
        _userInfor.emit(temp)
        authRepository.updateInforUser("Bear $token", userInforRequest)
    }
}