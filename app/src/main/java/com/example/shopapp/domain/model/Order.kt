package com.example.shopapp.domain.model

data class Order(
    val count: Int,
    val price: Int,
    val productId: Int
)