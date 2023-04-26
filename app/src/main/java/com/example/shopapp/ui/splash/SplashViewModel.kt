package com.example.shopapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.LoginState
import com.example.shopapp.domain.repository.LoginRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val shopRepository: ShopRepository, private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    init {
        controlUser()
    }

    private fun controlUser() {
        viewModelScope.launch {
            loginRepository.getToken()?.let { token ->
                shopRepository.refreshToken(token.refreshToken) { result ->
                    when (result) {
                        is Resource.Success -> {
                            viewModelScope.launch {
                                loginRepository.deleteAll()
                                result.data?.let { loginRepository.addToken(it) }
                            }
                            _loginState.postValue(LoginState(isAuthenticated = true))
                        }
                        is Resource.Error -> _loginState.postValue(LoginState(error = result.message))
                    }
                }
            } ?: kotlin.run {
                _loginState.postValue(LoginState(isAuthenticated = false))
            }
        }
    }
}