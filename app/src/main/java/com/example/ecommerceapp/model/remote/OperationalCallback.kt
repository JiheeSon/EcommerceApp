package com.example.ecommerceapp.model.remote


interface OperationalCallback {
    fun onSuccess(data: Any)
    fun onFailure(message: String)
}