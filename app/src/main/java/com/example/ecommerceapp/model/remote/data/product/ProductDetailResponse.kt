package com.example.ecommerceapp.model.remote.data.product

data class ProductDetailResponse(
    val message: String,
    val product: ProductX,
    val status: Int
)