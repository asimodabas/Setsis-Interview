package com.example.shopapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shopapp.data.dto.Token

@Dao
interface TokenDAO {

    @Query("SELECT * FROM token")
    fun getAllToken(): List<Token>

    @Insert
    fun addToken(token: Token)

    @Query("DELETE FROM token")
    fun deleteAll()
}