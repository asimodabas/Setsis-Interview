package com.example.shopapp.ui.activity.basket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shopapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        supportActionBar?.hide()

    }
}