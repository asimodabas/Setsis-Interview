package com.example.shopapp.domain.repository

import com.example.shopapp.common.Resource
import com.example.shopapp.data.dto.CategoriesResponse
import com.example.shopapp.data.dto.Token

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
}