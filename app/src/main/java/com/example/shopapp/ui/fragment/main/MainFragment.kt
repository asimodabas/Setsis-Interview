package com.example.shopapp.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}