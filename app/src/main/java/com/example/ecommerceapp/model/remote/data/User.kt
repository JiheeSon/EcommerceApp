package com.example.ecommerceapp.model.remote.data

data class User(
    var user_id: String?,
    var full_name: String,
    var mobile_no: String,
    val email_id: String,
    val password: String
)