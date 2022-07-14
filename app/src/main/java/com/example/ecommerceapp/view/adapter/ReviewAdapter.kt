package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemReviewBinding
import com.example.ecommerceapp.model.remote.data.product.Review

class ReviewAdapter(private val reviewList: List<Review>): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private lateinit var binding: ItemReviewBinding

    inner class ReviewViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(review: Review) {
            binding.apply {
                textReviewUserName.text = review.review_title
                textReviewBody.text = review.review
                textReviewTitle.text = review.review_title
                ratingBarReview.rating = review.rating.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemReviewBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.apply {
            val review = reviewList[position]
            bind(review)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}