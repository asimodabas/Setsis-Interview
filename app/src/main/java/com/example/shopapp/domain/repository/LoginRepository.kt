package com.example.shopapp.domain.repository

import com.example.shopapp.data.dto.Token

interface LoginRepository {

    suspend fun getToken(): Token?

    suspend fun addToken(token: Token)

    suspend fun deleteAll()
}