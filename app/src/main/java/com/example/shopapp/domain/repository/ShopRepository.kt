package com.example.shopapp.domain.repository

import com.example.shopapp.common.Resource
import com.example.shopapp.data.dto.response.CategoriesResponse
import com.example.shopapp.data.dto.response.ProductResponse
import com.example.shopapp.data.dto.Token
import com.example.shopapp.domain.model.OrdersRequest

interface ShopRepository {
    fun logIn(
        usernameOrEmail: String, password: String, result: (Resource<Token?>) -> Unit
    )

    fun refreshToken(
        refreshToken: String, result: (Resource<Token?>) -> Unit
    )

    fun getCategories(
        accessToken: String, result: (Resource<CategoriesResponse?>) -> Unit
    )

    fun getRandomProduct(
        accessToken: String, result: (Resource<ProductResponse?>) -> Unit
    )

    fun getAllProduct(
        accessToken: String, result: (Resource<ProductResponse?>) -> Unit
    )

    fun sendAllProduct(
        accessToken: String, body: OrdersRequest, result: (Resource<Int?>) -> Unit
    )
}