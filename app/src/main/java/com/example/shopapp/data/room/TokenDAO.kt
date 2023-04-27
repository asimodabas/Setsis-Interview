package com.example.shopapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shopapp.data.dto.Product
import com.example.shopapp.data.dto.Token
import retrofit2.http.DELETE

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

    @Delete
    fun deleteProduct(product: Product)

    @Insert
    fun saveProduct(product: Product)
}