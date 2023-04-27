package com.example.shopapp.common.state

import com.example.shopapp.data.dto.ProductResponse

data class GetProductState(
    val success: ProductResponse? = null,
    val error: String? = null
)