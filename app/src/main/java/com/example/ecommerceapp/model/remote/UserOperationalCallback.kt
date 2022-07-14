package com.example.ecommerceapp.model.remote

interface UserOperationalCallback {
    fun onSuccess(status: Int, message: String)
    fun onFailure(message: String)
}