package com.example.furniture.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.User
import com.example.furniture.domain.repository.FavoriteRepository
import com.example.furniture.helper.Console
import com.example.furniture.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,

    ) : ViewModel() {
    val favoriteProducts: StateFlow<List<Product>>
        get() = favoriteRepository.favoriteProducts

    init {
        getFavorites()

    }

    fun getFavorites() {
        val sharedPreferences = favoriteRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "")

        viewModelScope.launch {
            favoriteRepository.getFavorites(("Bear $getToken"))
        }
    }

    private val _isConfirmRemove = MutableLiveData<Boolean>()
    val isConfirmRemove: LiveData<Boolean> get() = _isConfirmRemove


    fun removeFavorite(token: String, requestBodyFavorite: RequestBodyFavorite) {
        viewModelScope.launch {
            val response = favoriteRepository.removeFavorite(token, requestBodyFavorite)
            try {
                if (response.code() == 200) {
                    getFavorites()
                    _isConfirmRemove.postValue(false)
                    Log.e("DELETE FAVORITE SUCCESS", "REMOVE SUCCESS ")
                } else {
                    Log.e("ERROR DELETE FAVORITE", "REMOVE Failed ")
                }
            } catch (e: Exception) {
                Log.e("ERROR DELETE FAVORITE", "REMOVE Failed " + e.message.toString())
            }

        }
    }

    fun showDialogConfirm(isShow: Boolean) {
        _isConfirmRemove.postValue(isShow)
    }

    private val _isProductFavorite = MutableLiveData<Boolean>()
    val isProductFavorite: LiveData<Boolean> get() = _isProductFavorite
    suspend fun checkIsLike(token: String, productId: RequestBodyFavorite) {
        val response = favoriteRepository.checkIsLike(token, productId)
        when (response) {
            is Resource.Error -> {
                _isProductFavorite.postValue(false)
            }

            is Resource.ErrorRes -> {
                _isProductFavorite.postValue(false)
            }

            is Resource.Success -> {
                _isProductFavorite.postValue(true)
            }
        }

    }
     fun createFavorite(token: String, productId: RequestBodyFavorite) {
       viewModelScope.launch {
           try {
               val response = favoriteRepository.createFavorite(token, productId)
               if (response.code() == 201) {
                   _isProductFavorite.postValue(true)
               }
           }catch (e: Exception) {
               Console().log("ERROR CREATE FAVORITE", "${e.message}")
           }
       }
    }
    fun toggleStatusFavorite(){
        _isProductFavorite.value = !_isProductFavorite.value!!
    }
}