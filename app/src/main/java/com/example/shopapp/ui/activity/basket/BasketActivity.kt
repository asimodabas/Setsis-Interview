package com.example.shopapp.ui.activity.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.ActivityBasketBinding
import com.example.shopapp.databinding.PayLayoutBinding
import com.example.shopapp.domain.model.Order
import com.example.shopapp.domain.model.OrdersRequest
import com.example.shopapp.ui.activity.basket.adapter.BasketRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityBasketBinding::inflate)
    private val viewModel by viewModels<BasketViewModel>()
    private lateinit var adapter: BasketRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        adapter = BasketRecyclerAdapter(viewModel)
        binding.basketRV.layoutManager = LinearLayoutManager(this)
        binding.basketRV.adapter = adapter

        viewModel.productsState.observe(this) { products ->
            adapter.submitList(products)
        }

        binding.payFab.setOnClickListener {
            openAlert()
        }
    }

    private fun openAlert() {
        val dialogView = PayLayoutBinding.inflate(LayoutInflater.from(this))
        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView.root)
            .setNegativeButton(getString(R.string.basket_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.basket_accept)) { dialog, _ ->
                handlePayment(dialogView)
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }

    private fun handlePayment(view: PayLayoutBinding) {
        if (view.nameET.text.isNotEmpty() &&
            view.cardNumberET.text.isNotEmpty() &&
            view.mounthET.text.isNotEmpty() &&
            view.yearET.text.isNotEmpty() &&
            view.cvvET.text.isNotEmpty()
        ) {
            val ordersList = adapter.currentList.map { product ->
                Order(
                    count = product.count,
                    price = product.price.toInt(),
                    productId = product.id
                )
            }

            val request = OrdersRequest(ordersList)
            viewModel.requestOrder(request)

            viewModel.orderState.observe(this) { state ->
                if (state.requireRedirect) {
                    finish()
                }
                state.error?.let { message ->
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(this, getString(R.string.basket_pay_success), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.basket_fill_blanks), Toast.LENGTH_SHORT).show()
        }
    }
}
