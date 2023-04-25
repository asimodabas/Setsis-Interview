package com.example.shopapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.shopapp.common.Constants
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.ActivitySplashScreenBinding
import com.example.shopapp.ui.activity.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashScreenBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, Constants.SPLASH_TIME)
    }
}