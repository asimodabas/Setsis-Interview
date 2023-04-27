package com.example.shopapp.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.data.dto.Product
import com.example.shopapp.databinding.RowProductItemBinding

class HomeRecyclerAdapter : ListAdapter<Product, HomeRecyclerAdapter.HomeViewHolder>(DiffCallback) {

    class HomeViewHolder(private val binding: RowProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                categoryIdTV.text = product.categoryId.toString()
                createdDateTV.text = product.createdDate
                priceTV.text = product.price.toString()
                productNameTV.text = product.productName
                stockTV.text = product.stock.toString()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            RowProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentList = currentList[position]
        holder.bind(currentList)
    }
}