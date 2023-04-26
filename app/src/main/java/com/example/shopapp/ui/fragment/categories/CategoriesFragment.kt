package com.example.shopapp.ui.fragment.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding by viewBinding(FragmentCategoriesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}