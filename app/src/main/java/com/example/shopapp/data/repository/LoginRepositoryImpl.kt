package com.example.shopapp.data.repository

import com.example.shopapp.data.dto.Token
import com.example.shopapp.data.room.TokenDAO
import com.example.shopapp.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dao: TokenDAO
) : LoginRepository {

    override suspend fun getToken(): Token? = withContext(Dispatchers.IO) {
        dao.getAllToken().firstOrNull()
    }

    override suspend fun addToken(token: Token) = withContext(Dispatchers.IO) {
        dao.addToken(token)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }
}