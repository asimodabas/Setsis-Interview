package com.example.shopapp.common.state

data class OrderState(
    val requireRedirect : Boolean = false,
    val error: String? = null
)