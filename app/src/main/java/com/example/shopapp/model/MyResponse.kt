package com.example.shopapp.model

import com.google.gson.annotations.SerializedName


data class MyResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String
)