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

            payFab.setOnClickListener {
                val v = PayLayoutBinding.inflate(LayoutInflater.from(this@BasketActivity))
                AlertDialog.Builder(this@BasketActivity).setView(v.root)
                    .setNegativeButton(getString(R.string.basket_cancel)) { dialog, _ ->
                        dialog.dismiss()
                    }.setPositiveButton(getString(R.string.basket_accept)) { dialog, _ ->
                        if (v.nameET.text.isNotEmpty() && v.cardNumberET.text.isNotEmpty() && v.mounthET.text.isNotEmpty() && v.yearET.text.isNotEmpty() && v.cvvET.text.isNotEmpty()) {
                            Toast.makeText(
                                this@BasketActivity,
                                getString(R.string.basket_pay_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@BasketActivity,
                                getString(R.string.basket_fill_blanks),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }.create().show()
            }
            basketRV.layoutManager = LinearLayoutManager(this@BasketActivity)
            basketRV.adapter = adapter
        }
    }
}