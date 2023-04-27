package com.example.shopapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.GetProductState
import com.example.shopapp.data.dto.Product
import com.example.shopapp.domain.repository.RoomRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val shopRepository: ShopRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _randomProductState = MutableLiveData<GetProductState>()
    val randomProductState: LiveData<GetProductState> = _randomProductState

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            roomRepository.getToken()?.let { token ->
                shopRepository.getRandomProduct(token.accessToken) { result ->
                    when (result) {
                        is Resource.Success -> _randomProductState.postValue(
                            GetProductState(
                                success = result.data
                            )
                        )
                        is Resource.Error -> _randomProductState.postValue(
                            GetProductState(
                                error = result.message
                            )
                        )
                    }
                }
            }
        }
    }

    fun saveProduct(product: Product) = viewModelScope.launch {
        roomRepository.saveProduct(product)
    }
}
