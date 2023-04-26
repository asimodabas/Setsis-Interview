package com.example.shopapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "token")
data class Token(
    @PrimaryKey(autoGenerate = true) var uuid: Int = 0,
    @ColumnInfo(name = "accessToken") @SerializedName("accessToken") var accessToken: String,
    @ColumnInfo(name = "expiration") @SerializedName("expiration") var expiration: String,
    @ColumnInfo(name = "refreshToken") @SerializedName("refreshToken") var refreshToken: String
)