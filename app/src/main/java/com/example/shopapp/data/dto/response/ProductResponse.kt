package com.example.shopapp.data.dto.response

import com.example.shopapp.data.dto.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products") val products: List<Product>
)