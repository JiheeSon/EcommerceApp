package com.example.ecommerceapp.model.remote

interface OperationalCallback {
    fun onSuccess(message: String)
    fun onFailure(message: String)
}