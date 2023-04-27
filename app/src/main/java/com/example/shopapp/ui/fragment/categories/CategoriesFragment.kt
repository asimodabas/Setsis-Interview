package com.example.shopapp.ui.fragment.categories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.databinding.FragmentCategoriesBinding
import com.example.shopapp.ui.fragment.categories.adapter.CategoriesRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding by viewBinding(FragmentCategoriesBinding::bind)
    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var recyclerAdapter: CategoriesRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        observeCategoryData()
    }

    private fun observeCategoryData() {
        viewModel.categoriesState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                state.success?.let { response ->
                    response.categories.let { recyclerAdapter.submitList(it) }
                    categoriesRV.isVisible = true
                }

                state.error?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRv() {
        binding.categoriesRV.apply {
            recyclerAdapter = CategoriesRecyclerAdapter()
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            clipToPadding = false
        }
    }
}