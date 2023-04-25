package com.example.shopapp.data.service

import com.example.shopapp.data.dto.TokenResponse
import com.example.shopapp.model.Product
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ShopAPI {

    @POST("api/Auth/Login")
    fun logIn(
        @Field("usernameOrEmail") usernameOrEmail: String,
        @Field("password") password: String
    ): Call<TokenResponse>

    @POST("/api/Auth/RefreshTokenLogin")
    fun refreshToken(
        @Field("refreshToken") refreshToken: String
    ): Call<TokenResponse>

    @GET("api/Category/GetAll")
    fun getAllCategories(
        @Header("Authorization") accessToken: String
    ): Call<Product>


}