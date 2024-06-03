package com.example.furniture.data.model.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String = "",

    @SerializedName("passWord")
    val passWord: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("avatar")
    val avatar: String = "",

    @SerializedName("token")
    val token: String = ""

)