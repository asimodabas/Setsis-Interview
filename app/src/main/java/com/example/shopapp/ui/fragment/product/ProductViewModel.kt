package com.example.shopapp.ui.fragment.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.GetRandomProductState
import com.example.shopapp.domain.repository.LoginRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel
@Inject constructor(
    private val shopRepository: ShopRepository,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _productState = MutableLiveData<GetRandomProductState>()

    init {
        getRandomProduct()
    }

    private fun getRandomProduct() {
        viewModelScope.launch {
            loginRepository.getToken()?.let { token ->
                shopRepository.getRandomProduct(token.accessToken) { result ->
                    when (result) {
                        is Resource.Success -> _productState.postValue(GetRandomProductState(success = result.data))
                        is Resource.Error -> _productState.postValue(GetRandomProductState(error = result.message))
                    }
                }
            }
        }
    }
}