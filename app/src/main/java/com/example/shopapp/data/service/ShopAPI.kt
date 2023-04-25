package com.example.shopapp.data.service

import com.example.shopapp.data.dto.TokenResponse
import com.example.shopapp.domain.model.LoginRequest
import retrofit2.Call
import retrofit2.http.*

interface ShopAPI {

    @POST("api/Auth/Login")
    fun logIn(
        @Body body: LoginRequest,
    ): Call<TokenResponse>

    @POST("/api/Auth/RefreshTokenLogin")
    fun refreshToken(
        @Field("refreshToken") refreshToken: String
    ): Call<TokenResponse>

    @GET("api/Category/GetAll")
    fun getAllCategories(
        @Header("Authorization") accessToken: String
    )


}