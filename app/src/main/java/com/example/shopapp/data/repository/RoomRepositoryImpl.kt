package com.example.shopapp.data.repository

import com.example.shopapp.data.dto.Product
import com.example.shopapp.data.dto.Token
import com.example.shopapp.data.room.TokenDAO
import com.example.shopapp.domain.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val dao: TokenDAO
) : RoomRepository {

    override suspend fun getToken(): Token? = withContext(Dispatchers.IO) {
        dao.getAllToken().lastOrNull()
    }

    override suspend fun addToken(token: Token) = withContext(Dispatchers.IO) {
        dao.addToken(token)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }

    override suspend fun deleteProduct(product: Product) = withContext(Dispatchers.IO) {
        dao.deleteProduct(product)
    }

    override suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        dao.getAllProduct()
    }

    override suspend fun saveProduct(product: Product) = withContext(Dispatchers.IO) {
        dao.saveProduct(product)
    }
}