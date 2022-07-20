package com.example.ecommerceapp.model.remote.data.orderdetail

data class OrderDetailResponse(
    val message: String,
    val order: Order,
    val status: Int
)