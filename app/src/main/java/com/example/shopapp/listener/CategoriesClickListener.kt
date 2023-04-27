package com.example.shopapp.listener

import com.example.shopapp.data.dto.Category

interface CategoriesClickListener {
    fun onClickItem(category: Category)
}
