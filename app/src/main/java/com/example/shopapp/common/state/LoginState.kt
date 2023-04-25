package com.example.shopapp.common.state

import com.example.shopapp.data.dto.Token

data class LoginState(
    val success: Token? = null,
    val error: String? = null
)
