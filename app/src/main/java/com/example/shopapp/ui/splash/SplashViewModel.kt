package com.example.shopapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.LoginState
import com.example.shopapp.domain.repository.RoomRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val shopRepository: ShopRepository, private val roomRepository: RoomRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    init {
        controlUser()
    }

    private fun controlUser() {
        viewModelScope.launch {
            roomRepository.getToken()?.let { token ->
                shopRepository.refreshToken(token.refreshToken) { result ->
                    when (result) {
                        is Resource.Success -> {
                            viewModelScope.launch {
                                roomRepository.deleteAll()
                                result.data?.let { roomRepository.addToken(it) }
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