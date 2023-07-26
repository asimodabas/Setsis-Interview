package com.example.shopapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.ActivitySplashScreenBinding
import com.example.shopapp.ui.activity.login.LoginActivity
import com.example.shopapp.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashScreenBinding::inflate)
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel.loginState.observe(this) { state ->
            val intent = if (state.isAuthenticated) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }
    }
}