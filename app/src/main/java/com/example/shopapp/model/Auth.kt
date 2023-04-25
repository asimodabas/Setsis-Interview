package com.example.shopapp.model

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("usernameOrEmail") val usernameOrEmail: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("refreshToken") val refreshToken: String?
)