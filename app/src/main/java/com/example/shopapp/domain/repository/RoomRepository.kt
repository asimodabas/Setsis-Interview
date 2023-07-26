package com.example.shopapp.domain.repository

import com.example.shopapp.data.dto.Product
import com.example.shopapp.data.dto.Token

interface RoomRepository {
    suspend fun getToken(): Token?

    suspend fun addToken(token: Token)

    suspend fun deleteAll()

    suspend fun deleteAllProduct()

    suspend fun deleteProduct(product: Product)

    suspend fun getProducts(): List<Product>

    suspend fun saveProduct(product: Product)

    suspend fun minusProductCount(productId: Int)

    suspend fun plusProductCount(productId: Int)
}