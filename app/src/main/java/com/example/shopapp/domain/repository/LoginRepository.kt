package com.example.shopapp.domain.repository

import com.example.shopapp.data.dto.Token
import com.example.shopapp.data.room.TokenDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository
@Inject constructor(
    private val dao: TokenDAO
) {
    suspend fun addToken(token: Token) = withContext(Dispatchers.IO) {
        dao.addToken(token)
    }
}