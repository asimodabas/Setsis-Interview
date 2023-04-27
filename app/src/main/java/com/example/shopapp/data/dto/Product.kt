package com.example.shopapp.data.dto

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("category") val category: Any,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("productName") val productName: String,
    @SerializedName("stock") val stock: Int
)