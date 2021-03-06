package com.example.ecommerceapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemProductBinding
import com.example.ecommerceapp.model.remote.data.Constants.BASE_IMAGE_URL
import com.example.ecommerceapp.model.remote.data.Constants.PRODUCT_ID
import com.example.ecommerceapp.model.remote.data.subcategory.Product
import com.example.ecommerceapp.view.activity.ProductDetailActivity

class ProductAdapter(private val productList: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var binding: ItemProductBinding

    inner class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(view.context)
                    .load(BASE_IMAGE_URL + product.product_image_url)
                    .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .fallback(R.drawable.ic_baseline_image_not_supported_24)
                    .into(imageProduct)
                textProductName.text = product.product_name
                textProductDescription.text = product.description
                textProductPrice.text = "$ ${product.price}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.apply {
            val product = productList[position]
            bind(product)

            itemView.setOnClickListener {
                val intent = Intent(view.context, ProductDetailActivity::class.java)
                intent.putExtra(PRODUCT_ID, product.product_id)
                view.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}