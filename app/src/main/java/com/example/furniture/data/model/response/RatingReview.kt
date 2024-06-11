package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

open class RatingReview(

    id: String,
    name: String,
    price: Int,
    image: String,
    description: String,
    type: String,
    userId: String,
    val rate: Int,
    val comment: String,
    val time: String
) : Product(id = id, name, price, image, description, type, userId)