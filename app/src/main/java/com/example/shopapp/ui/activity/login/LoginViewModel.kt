package com.example.shopapp.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.SingleLiveEvent
import com.example.shopapp.common.state.LoginState
import com.example.shopapp.data.dto.Token
import com.example.shopapp.domain.repository.LoginRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository, private val shopRepository: ShopRepository
) : ViewModel() {

    private val _loginState = SingleLiveEvent<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun logIn(usernameOrEmail: String, password: String) {
        shopRepository.logIn(usernameOrEmail, password) { result ->
            when (result) {
                is Resource.Success -> _loginState.postValue(LoginState(success = result.data))
                is Resource.Error -> _loginState.postValue(LoginState(error = result.message))
            }
        }
    }

    fun addToken(token: Token) = viewModelScope.launch {
        loginRepository.addToken(token)
    }
}