package com.example.ecommerceapp.model.remote.data.order

data class OrderInput(
    val user_id: Int,
    val delivery_address: DeliveryAddress,
    val items: ArrayList<Item>,
    val bill_amount: Int,
    val payment_method: String
)