package com.example.shopapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Order(
    @PrimaryKey @SerializedName("productId") val productId: Int?,
    @SerializedName("price") val price: Int?,
    @SerializedName("count") val count: Int?
)