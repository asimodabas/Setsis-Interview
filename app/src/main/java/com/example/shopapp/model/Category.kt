package com.example.shopapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Category(
    @PrimaryKey @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("refreshToken") val refreshToken: String?
)