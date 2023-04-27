package com.example.shopapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.common.viewBinding
import com.example.shopapp.data.dto.Product
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.ui.fragment.home.adapter.HomeRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), HomeRecyclerAdapter.OnItemClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        observeCategoryData()
    }

    private fun observeCategoryData() {
        viewModel.randomProductState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                state.success?.let { response ->
                    response.products.let { homeRecyclerAdapter.submitList(it) }
                    homeRV.isVisible = true
                }

                state.error?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRv() {
        binding.homeRV.apply {
            homeRecyclerAdapter = HomeRecyclerAdapter(this@HomeFragment)
            adapter = homeRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            clipToPadding = false
        }
    }

    override fun onItemClick(product: Product) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.saveProduct(product)
        }
    }
}