package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemProductDetailImageBinding
import com.example.ecommerceapp.model.remote.data.Constants.BASE_IMAGE_URL
import com.example.ecommerceapp.model.remote.data.product.Image

class ProductDetailImageViewPagerAdapter(private val imageList: ArrayList<Image>): RecyclerView.Adapter<ProductDetailImageViewPagerAdapter.ImageViewHolder>() {
    private lateinit var binding: ItemProductDetailImageBinding
    inner class ImageViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind (image: Image) {
            binding.apply {
                Glide.with(view.context)
                    .load(BASE_IMAGE_URL + image.image)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .fallback(R.drawable.ic_baseline_image_not_supported_24)
                    .into(imageProductDetail)
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductDetailImageBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.apply {
            val image = imageList[position]
            bind(image)
        }
    }


}