package com.example.shopapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}