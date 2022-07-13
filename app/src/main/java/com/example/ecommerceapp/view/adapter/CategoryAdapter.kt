package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemCategoryBinding
import com.example.ecommerceapp.model.remote.data.Category
import com.example.ecommerceapp.model.remote.data.Constants.BASE_IMAGE_URL

class CategoryAdapter(private val categoryList: List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private lateinit var binding: ItemCategoryBinding

    inner class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            binding.apply {
                textCategoryName.text = category.category_name
                Glide.with(view.context)
                    .load(BASE_IMAGE_URL + category.category_image_url)
                    .into(imgCategory)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            val category = categoryList[position]
            bind(category)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}