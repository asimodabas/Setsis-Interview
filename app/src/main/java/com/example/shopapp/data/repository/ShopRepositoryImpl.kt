package com.example.shopapp.data.repository

import com.example.shopapp.common.Resource
import com.example.shopapp.data.dto.Token
import com.example.shopapp.data.service.ShopAPI
import com.example.shopapp.domain.repository.ShopRepository
import retrofit2.HttpException
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val service: ShopAPI
) : ShopRepository {

    override fun logIn(usernameOrEmail: String, password: String): Resource<Token?> = try {
        Resource.Success(service.logIn(usernameOrEmail, password).execute().body()?.token)
    } catch (e: HttpException) {
        Resource.Error(e.message())
    } catch (e: Exception) {
        Resource.Error(e.message.orEmpty())
    }
}