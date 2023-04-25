package com.example.shopapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.common.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SplashViewModel : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    private fun controlUser() {
        _loginState.postValue(LoginState(isAuthenticated = false))
    }
}