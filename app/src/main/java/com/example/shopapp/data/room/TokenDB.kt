package com.example.shopapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.data.dto.Product
import com.example.shopapp.data.dto.Token

@Database(entities = [Token::class, Product::class], version = 1)
abstract class TokenDB : RoomDatabase() {

    abstract fun getTokenDao(): TokenDAO
}