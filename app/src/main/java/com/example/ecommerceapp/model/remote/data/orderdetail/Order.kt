package com.example.ecommerceapp.model.remote.data.orderdetail

data class Order(
    val address: String,
    val address_title: String,
    val bill_amount: String,
    val items: List<Item>,
    val order_date: String,
    val order_id: String,
    val order_status: String,
    val payment_method: String
)