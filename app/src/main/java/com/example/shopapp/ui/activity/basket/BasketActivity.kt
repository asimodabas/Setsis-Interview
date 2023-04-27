package com.example.shopapp.ui.activity.basket

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.ActivityBasketBinding
import com.example.shopapp.ui.activity.basket.adapter.BasketRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityBasketBinding::inflate)
    private val viewModel by viewModels<BasketViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val adapter = BasketRecyclerAdapter(viewModel)
        with(binding) {

            viewModel.productsState.observe(this@BasketActivity) { products ->
                adapter.submitList(products)
            }

            basketRV.layoutManager = LinearLayoutManager(this@BasketActivity)
            basketRV.adapter = adapter
        }
    }
}