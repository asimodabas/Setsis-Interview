package com.example.shopapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) var uuid: Int = 0,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("productName") val productName: String,
    @SerializedName("stock") val stock: Int
)