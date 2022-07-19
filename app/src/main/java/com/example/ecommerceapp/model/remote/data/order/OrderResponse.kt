package com.example.ecommerceapp.model.remote.data.order

data class OrderResponse(
    val status: Int,
    val message: String,
    val order_id: Int
)