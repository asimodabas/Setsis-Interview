package com.example.shopapp.data.dto


import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("expiration")
    val expiration: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)