package com.example.shopapp.data.dto.response


import com.example.shopapp.data.dto.Token
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token") val token: Token
)