package com.example.furniture.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestBodyRating
import com.example.furniture.data.model.response.RatingReview
import com.example.furniture.data.model.response.ResponseRatingDetails
import com.example.furniture.domain.repository.RatingRepository
import com.example.furniture.services.OverallRating
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(private val ratingRepository: RatingRepository):ViewModel(){

    private val _ratingReviews = MutableStateFlow<List<RatingReview>>(emptyList())
    val ratingReviews : StateFlow<List<RatingReview>> get() = _ratingReviews
    private val _overallRatingProduct = MutableStateFlow<OverallRating>(OverallRating(0.0, 0))
    val overallRatingProduct: StateFlow<OverallRating> get() = _overallRatingProduct
    private val _ratingAnReviews = MutableStateFlow<List<ResponseRatingDetails>>(emptyList())
    val ratingAnReviews : StateFlow<List<ResponseRatingDetails>> get() = _ratingAnReviews

    init {
        viewModelScope.launch {
            getMyRatingAndReview()
        }
    }
    suspend fun getMyRatingAndReview() {
        val sharedPreferences = ratingRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = ratingRepository.getMyReviews("Bear $getToken")
        _ratingReviews.emit(res)
    }
    suspend fun createRating(requestBodyRating: RequestBodyRating): Boolean {
        val sharedPreferences = ratingRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = ratingRepository.createRating("Bear $getToken", requestBodyRating)
        return res.status == 201
    }

    suspend fun getOverallRating(productId: String) {
        val sharedPreferences = ratingRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = ratingRepository.countOverallRating("Bear $getToken", productId)
        _overallRatingProduct.emit(res)
    }

    suspend fun getRatingAndReviewsDetails(productId: String) {
        val sharedPreferences = ratingRepository.getSharedPreferences()
        val getToken = sharedPreferences.getString(Storage.TOKEN.toString(), "") ?: ""

        val res = ratingRepository.ratingDetails("Bear $getToken", productId)
        _ratingAnReviews.emit(res)
    }

}