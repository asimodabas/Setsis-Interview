package com.example.shopapp.data.repository

import com.example.shopapp.common.Resource
import com.example.shopapp.data.dto.Token
import com.example.shopapp.data.dto.TokenResponse
import com.example.shopapp.data.service.ShopAPI
import com.example.shopapp.domain.model.LoginRequest
import com.example.shopapp.domain.repository.ShopRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val service: ShopAPI
) : ShopRepository {

    override fun logIn(
        usernameOrEmail: String, password: String, result: (Resource<Token?>) -> Unit
    ) {
        try {
            service.logIn(LoginRequest(usernameOrEmail, password))
                .enqueue(object : Callback<TokenResponse> {
                    override fun onResponse(
                        call: Call<TokenResponse>, response: Response<TokenResponse>
                    ) {
                        response.body()?.token?.let {
                            result(Resource.Success(response.body()?.token))
                        } ?: kotlin.run {
                            result(Resource.Error("${response.raw().code}: ${response.raw().message}"))
                        }
                    }

                    override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                        result(Resource.Error(t.message.orEmpty()))
                    }
                })

        } catch (e: HttpException) {
            result(Resource.Error(e.message()))
        } catch (e: Exception) {
            result(Resource.Error(e.message.orEmpty()))
        }
    }


}