package com.example.shopapp.data.dto.response

import com.example.shopapp.data.dto.Category
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("categories") val categories: List<Category>
)