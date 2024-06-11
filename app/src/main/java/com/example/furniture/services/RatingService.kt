package com.example.furniture.services

import com.example.furniture.data.model.request.RequestBodyRating
import com.example.furniture.data.model.response.MessageResponse
import com.example.furniture.data.model.response.RatingReview
import com.example.furniture.data.model.response.ResponseRatingDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

data class OverallRating(
    val averageRating: Double,
    val reviews: Int
)

interface RatingService {
    @POST("/api/review/")
    suspend fun createRatingAndReview(
        @Header("Authorization") token: String,
       @Body requestBodyRating: RequestBodyRating
    ): Response<MessageResponse>

    @GET("/api/review/")
    suspend fun getMyRatingAndReview(@Header("Authorization") token: String):
            Response<List<RatingReview>>

    @GET("/api/review/count/{productId}")
    suspend fun countRatingOfProduct(
        @Header("Authorization") token: String,
        @Path("productId") productId: String
    ):Response<OverallRating>

    @GET("/api/review/details/{productId}")
    suspend fun ratingDetails(
        @Header("Authorization") token: String,
        @Path("productId") productId: String
    ):Response<List<ResponseRatingDetails>>
}