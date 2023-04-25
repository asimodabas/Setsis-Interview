package com.example.shopapp.room

import com.example.shopapp.model.MyResponse
import com.example.shopapp.model.Product
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ShopAPI {

    @POST("api/Auth/Login")
    fun loginUser(
        @Field("usernameOrEmail") usernameOrEmail: String = "testuser",
        @Field("password") password: String = "123456"
    ): Call<MyResponse>

    @GET("api/Category/GetAll")
    fun getAllCategories(): Call<Product>
}