package com.example.shopapp.ui.activity.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.SingleLiveEvent
import com.example.shopapp.common.state.OrderState
import com.example.shopapp.data.dto.Product
import com.example.shopapp.domain.model.OrdersRequest
import com.example.shopapp.domain.repository.RoomRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel
@Inject constructor(
    private val roomRepository: RoomRepository,
    private val shopRepository: ShopRepository
) : ViewModel() {

    private val _productsState = MutableLiveData<List<Product>>()
    val productsState: LiveData<List<Product>> = _productsState

    private val _orderState = SingleLiveEvent<OrderState>()
    val orderState: LiveData<OrderState> = _orderState

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        _productsState.postValue(roomRepository.getProducts())
    }

    fun requestOrder(ordersRequest: OrdersRequest) = viewModelScope.launch {
        roomRepository.getToken()?.let { token ->
            shopRepository.sendAllProduct(token.accessToken, ordersRequest) { result ->
                when (result) {
                    is Resource.Success -> {
                        viewModelScope.launch {
                            roomRepository.deleteAllProduct()
                        }
                        _orderState.postValue(OrderState(requireRedirect = true))
                    }
                    is Resource.Error -> {
                        _orderState.postValue(OrderState(error = result.message))
                    }
                }
            }
        }

    }

    fun deleteProduct(product: Product) = viewModelScope.launch {
        roomRepository.deleteProduct(product)
        getProducts()
    }

    fun minusProductCount(productId: Int) = viewModelScope.launch {
        roomRepository.getProducts().let { products ->
            products.find { it.id == productId }?.let { product ->
                if (product.count == 1) {
                    deleteProduct(product)
                    getProducts()
                } else {
                    roomRepository.minusProductCount(productId)
                    getProducts()
                }
            }
        }
    }

    fun plusProductCount(productId: Int) = viewModelScope.launch {
        roomRepository.plusProductCount(productId)
        getProducts()
    }
}