package com.example.shopapp.common.state

import com.example.shopapp.data.dto.response.CategoriesResponse

data class GetCategoriesState(
    val success: CategoriesResponse? = null,
    val error: String? = null
)