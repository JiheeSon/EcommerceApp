package com.example.ecommerceapp.model.remote.data.orderList

data class Order(
    val address: String,
    val address_title: String,
    val bill_amount: String,
    val order_date: String,
    val order_id: String,
    val order_status: String,
    val payment_method: String
)