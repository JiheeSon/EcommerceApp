package com.example.ecommerceapp.model.remote

import com.example.ecommerceapp.model.remote.data.User

interface UserOperationalCallback {
    fun onSuccess(status: Int, message: String)
    fun onFailure(message: String)
}