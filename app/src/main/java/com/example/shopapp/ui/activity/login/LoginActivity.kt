package com.example.shopapp.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.shopapp.databinding.ActivityLoginBinding
import com.example.shopapp.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

            binding.loginButton.setOnClickListener {
                val userNameET = binding.userNameET.text
                val passwordET = binding.PasswordET.text

                if (!(userNameET.isNullOrEmpty() && passwordET.isNullOrEmpty())) {
                    viewModel.logIn(userNameET.toString(), passwordET.toString())
                    viewModel.loginState.observe(this) { state ->
                        state.success?.let { token ->
                            Log.d(
                                "TAG",
                                "accessToken: ${token.accessToken} \n refreshToken: ${token.refreshToken}"
                            )

                            intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }

                        state.error?.let { message ->
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        }

                        Log.d("TAG", "onCreate: $state")
                    }
                } else {
                    Toast.makeText(this, "Please fill in the blanks!", Toast.LENGTH_SHORT).show()
                }
            }

    }


}