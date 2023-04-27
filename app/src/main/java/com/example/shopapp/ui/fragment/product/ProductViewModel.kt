package com.example.shopapp.ui.fragment.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.GetProductState
import com.example.shopapp.domain.repository.RoomRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel
@Inject constructor(
    private val shopRepository: ShopRepository,
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _productState = MutableLiveData<GetProductState>()

    init {
        getRandomProduct()
    }

    private fun getRandomProduct() {
        viewModelScope.launch {
            roomRepository.getToken()?.let { token ->
                shopRepository.getRandomProduct(token.accessToken) { result ->
                    when (result) {
                        is Resource.Success -> _productState.postValue(GetProductState(success = result.data))
                        is Resource.Error -> _productState.postValue(GetProductState(error = result.message))
                    }
                }
            }
        }
    }
}