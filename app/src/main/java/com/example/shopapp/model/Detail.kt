package com.example.shopapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Detail(
    @PrimaryKey @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("count") val count: String?,
    @SerializedName("stock") val stock: String?,
    @SerializedName("productId") var productId: String?
)