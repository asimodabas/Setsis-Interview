package com.example.shopapp.ui.activity.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.data.dto.Product
import com.example.shopapp.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel
@Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _productsState = MutableLiveData<List<Product>>()
    val productsState: LiveData<List<Product>> = _productsState

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        _productsState.postValue(roomRepository.getProducts())
    }

    fun deleteProduct(product: Product) = viewModelScope.launch {
        roomRepository.deleteProduct(product)
        getProducts()
    }
}