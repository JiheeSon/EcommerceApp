package com.example.ecommerceapp.model.remote.data

data class CartItem(
    val productId: String,
    val productName: String,
    val productDescription: String,
    val productPrice: String,
    val productImage: String,
    val amount: Int
)
