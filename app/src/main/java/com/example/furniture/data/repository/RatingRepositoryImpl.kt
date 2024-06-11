package com.example.furniture.data.repository

import android.content.SharedPreferences
import com.example.furniture.data.model.request.RequestBodyRating
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.RatingReview
import com.example.furniture.data.model.response.ResponseRatingDetails
import com.example.furniture.domain.repository.RatingRepository
import com.example.furniture.helper.ConsoleLog
import com.example.furniture.services.OverallRating
import com.example.furniture.services.RatingService

class RatingRepositoryImpl(
    private val ratingService: RatingService,
    private val getSharedPreferences: SharedPreferences
) : RatingRepository {
    override fun getSharedPreferences(): SharedPreferences {
        return getSharedPreferences
    }

    override suspend fun getMyReviews(token: String): List<RatingReview> {
        try {
            val res = ratingService.getMyRatingAndReview(token)
            return res.body()!!
        } catch (e: Exception) {
            ConsoleLog("ERROR MY REVIEWS", "ERROR GET MY REVIEW")
            return emptyList()
        }
    }

    override suspend fun createRating(
        token: String,
        requestBodyRating: RequestBodyRating
    ): MessageResponse {
        try {
            val res = ratingService.createRatingAndReview(token, requestBodyRating)
            return if (res.isSuccessful) {
                MessageResponse("CREATE NEW RATING AND REVIEW SUCCESS", 201)
            }else{
                MessageResponse("ERROR CREATE", 500)
            }
        }catch (e: Exception) {
            return MessageResponse("ERROR SERVER", 500)
        }
    }

    override suspend fun countOverallRating(token: String, productId: String): OverallRating {
        try {
            val res = ratingService.countRatingOfProduct(token, productId)
            return res.body()!!
        }catch (e: Exception) {
            return OverallRating(0.0,0)
        }
    }

    override suspend fun ratingDetails(token: String, productId: String): List<ResponseRatingDetails> {
        try {
            val res = ratingService.ratingDetails(token, productId)
            return res.body()!!
        }catch (e: Exception) {
            return emptyList()
        }
    }
}