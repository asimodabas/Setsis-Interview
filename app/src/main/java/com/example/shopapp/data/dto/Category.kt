package com.example.shopapp.data.dto

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("products") val products: Any
)