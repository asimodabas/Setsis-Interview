package com.example.shopapp.di

import android.content.Context
import androidx.room.Room
import com.example.shopapp.data.room.TokenDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFavDatabase(@ApplicationContext context: Context): TokenDB {
        return Room.databaseBuilder(
            context, TokenDB::class.java, "tokendb"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideFavDao(tokenDb: TokenDB) = tokenDb.getTokenDao()
}