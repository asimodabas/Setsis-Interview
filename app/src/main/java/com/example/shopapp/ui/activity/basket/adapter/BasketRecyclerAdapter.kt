package com.example.shopapp.ui.activity.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.data.dto.Product
import com.example.shopapp.databinding.RowBasketItemBinding
import com.example.shopapp.ui.activity.basket.BasketViewModel

class BasketRecyclerAdapter(private val viewModel: BasketViewModel) :
    ListAdapter<Product, BasketRecyclerAdapter.BasketViewHolder>(DiffCallback) {

    class BasketViewHolder(
        private val binding: RowBasketItemBinding, private val viewModel: BasketViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            categoryIdTV.text = product.categoryId.toString()
            createdDateTV.text = product.createdDate
            priceTV.text = product.price.toString()
            productNameTV.text = product.productName
            stockTV.text = product.stock.toString()
            numberTV.text = product.count.toString()

            deleteBasketIv.setOnClickListener { viewModel.deleteProduct(product) }
            plusTV.setOnClickListener { viewModel.plusProductCount(product.id) }
            minusTV.setOnClickListener { viewModel.minusProductCount(product.id) }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            RowBasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val currentList = currentList[position]
        holder.bind(currentList)
    }
}