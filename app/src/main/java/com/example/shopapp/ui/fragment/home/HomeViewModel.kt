package com.example.shopapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.GetRandomProductState
import com.example.shopapp.data.dto.Product
import com.example.shopapp.domain.repository.LoginRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val shopRepository: ShopRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _randomProductState = MutableLiveData<GetRandomProductState>()
    val randomProductState: LiveData<GetRandomProductState> = _randomProductState

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            loginRepository.getToken()?.let { token ->
                shopRepository.getRandomProduct(token.accessToken) { result ->
                    when (result) {
                        is Resource.Success -> _randomProductState.postValue(
                            GetRandomProductState(
                                success = result.data
                            )
                        )
                        is Resource.Error -> _randomProductState.postValue(
                            GetRandomProductState(
                                error = result.message
                            )
                        )
                    }
                }
            }
        }
    }

    fun saveProduct(product: Product) = viewModelScope.launch {
        loginRepository.saveProduct(product)
    }
}
