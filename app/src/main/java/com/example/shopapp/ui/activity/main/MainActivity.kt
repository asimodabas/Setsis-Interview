package com.example.shopapp.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityMainBinding
import com.example.shopapp.ui.activity.basket.BasketActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupBottomNavigationView()
        setupFabClickListener()
        setupNavigation()
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
    }

    private fun setupFabClickListener() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this, BasketActivity::class.java))
        }
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.fContainerFlow)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.categoriesFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
