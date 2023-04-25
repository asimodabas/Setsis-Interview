package com.example.shopapp.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("username") val username: String?,
    @SerializedName("firstname") val firstname: String?,
    @SerializedName("lastname") val lastname: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?
)