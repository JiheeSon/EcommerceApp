package com.example.ecommerceapp.model.remote.data

data class SubCategoryProductListResponse(
    val message: String,
    val products: ArrayList<Product>,
    val status: Int
)