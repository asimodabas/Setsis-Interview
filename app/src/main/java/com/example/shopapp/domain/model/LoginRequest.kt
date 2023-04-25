package com.example.shopapp.domain.model

data class LoginRequest(
    val usernameOrEmail : String,
    val password: String
)