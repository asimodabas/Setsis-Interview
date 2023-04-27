package com.example.shopapp.ui.fragment.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.Resource
import com.example.shopapp.common.state.GetCategoriesState
import com.example.shopapp.domain.repository.LoginRepository
import com.example.shopapp.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel
@Inject constructor(
    private val shopRepository: ShopRepository,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _categoriesState = MutableLiveData<GetCategoriesState>()
    val categoriesState: LiveData<GetCategoriesState> = _categoriesState

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            loginRepository.getToken()?.let { token ->
                shopRepository.getCategories(token.accessToken) { result ->
                    when (result) {
                        is Resource.Success -> _categoriesState.postValue(GetCategoriesState(success = result.data))
                        is Resource.Error -> _categoriesState.postValue(GetCategoriesState(error = result.message))
                    }
                }
            }
        }
    }
}