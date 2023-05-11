package com.example.shopapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shopapp.data.dto.Product
import com.example.shopapp.data.dto.Token

@Dao
interface TokenDAO {

    @Query("SELECT * FROM token")
    fun getAllToken(): List<Token>

    @Query("SELECT * FROM product")
    fun getAllProduct(): List<Product>

    @Insert
    fun addToken(token: Token)

    @Query("DELETE FROM token")
    fun deleteAll()

    @Query("DELETE FROM product")
    fun deleteAllProduct()

    @Delete
    fun deleteProduct(product: Product)

    @Insert
    fun saveProduct(product: Product)

    @Query("UPDATE product SET count = count+1 WHERE id = :productId")
    fun plusProductCount(productId: Int)

    @Query("UPDATE product SET count = count-1 WHERE id = :productId")
    fun minusProductCount(productId: Int)
}