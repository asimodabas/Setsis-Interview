package com.example.shopapp.ui.fragment.categories_detail

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
class CategoriesDetailViewModel
@Inject constructor(
    private val roomRepository: RoomRepository, private val shopRepository: ShopRepository
) : ViewModel() {

    private val _productsState = MutableLiveData<GetProductState>()
    val productsState: LiveData<GetProductState> = _productsState

    fun getProductsByCategoryId(categoryId: Int) = viewModelScope.launch {
        roomRepository.getToken()?.let { token ->
            shopRepository.getAllProduct(token.accessToken) { result ->
                when (result) {
                    is Resource.Success -> _productsState.postValue(GetProductState(success = result.data))
                    is Resource.Error -> _productsState.postValue(GetProductState(error = result.message))
                }
            }
        }
    }

    fun saveProduct(product: Product) = viewModelScope.launch {
        roomRepository.saveProduct(product)
    }
}