package com.example.furniture.domain.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.request.RequestBodyRating
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.RatingReview
import com.example.furniture.data.model.response.ResponseRatingDetails
import com.example.furniture.services.OverallRating

interface RatingRepository {
    fun getSharedPreferences(): SharedPreferences
    suspend fun getMyReviews(token: String) : List<RatingReview>
    suspend fun createRating(token: String, requestBodyRating: RequestBodyRating): MessageResponse
    suspend fun countOverallRating(token: String, productId:String) : OverallRating
    suspend fun ratingDetails(token: String, productId:String): List<ResponseRatingDetails>
}