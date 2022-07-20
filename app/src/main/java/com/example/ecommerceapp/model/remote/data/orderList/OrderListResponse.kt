package com.example.ecommerceapp.model.remote.data.orderList

data class OrderListResponse(
    val message: String,
    val orders: List<Order>,
    val status: Int
)