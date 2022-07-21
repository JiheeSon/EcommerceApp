package com.example.ecommerceapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemCategoryBinding
import com.example.ecommerceapp.model.remote.data.category.Category
import com.example.ecommerceapp.model.remote.data.Constants.BASE_IMAGE_URL
import com.example.ecommerceapp.view.activity.SubCategoryActivity

class CategoryAdapter(private val categoryList: List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private lateinit var binding: ItemCategoryBinding

    inner class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            binding.apply {
                textCategoryName.text = category.category_name
                Glide.with(view.context)
                    .load(BASE_IMAGE_URL + category.category_image_url)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .fallback(R.drawable.ic_baseline_image_not_supported_24)
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

            itemView.setOnClickListener {
                val intent = Intent(view.context, SubCategoryActivity::class.java)
                intent.putExtra("category_id", category.category_id)
                view.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}