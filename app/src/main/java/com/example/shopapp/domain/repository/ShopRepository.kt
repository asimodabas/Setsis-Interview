package com.example.shopapp.domain.repository

import com.example.shopapp.common.Resource
import com.example.shopapp.data.dto.Token

interface ShopRepository {
    fun logIn(
        usernameOrEmail: String,
        password: String,
        result: (Resource<Token?>) -> Unit
    )
}