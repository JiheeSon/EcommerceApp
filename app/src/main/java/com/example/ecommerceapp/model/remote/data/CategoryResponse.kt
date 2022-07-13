package com.example.ecommerceapp.model.remote.data

data class CategoryResponse(
    val categories: ArrayList<Category>,
    val message: String,
    val status: Int
)