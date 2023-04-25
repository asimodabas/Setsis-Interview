package com.example.shopapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("name") @Expose val name: String?,
    @SerializedName("price") @Expose val price: Int?,
    @SerializedName("stock") @Expose val stock: Int?,
    @SerializedName("categoryId") @Expose val categoryId: Int?
)