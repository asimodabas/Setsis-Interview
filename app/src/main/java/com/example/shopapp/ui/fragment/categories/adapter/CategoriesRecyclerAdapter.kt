package com.example.shopapp.ui.fragment.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.data.dto.Category
import com.example.shopapp.databinding.RowCategoriesItemBinding
import com.example.shopapp.ui.fragment.categories.CategoriesFragmentDirections

class CategoriesRecyclerAdapter(
    private val navController: NavController
) :
    ListAdapter<Category, CategoriesRecyclerAdapter.CategoriesViewHolder>(DiffCallback) {

    class CategoriesViewHolder(
        private val binding: RowCategoriesItemBinding,
        private val navController: NavController
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            with(binding) {
                categoryIdTV.text = category.id.toString()
                categoryNameTV.text = category.categoryName
                createdDateTV.text = category.createdDate

                root.setOnClickListener {
                    navController.navigate(
                        CategoriesFragmentDirections.actionCategoriesFragmentToCategoriesDetailFragment(
                            category.id
                        )
                    )
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            RowCategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            navController
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val currentList = currentList[position]
        holder.bind(currentList)
    }
}