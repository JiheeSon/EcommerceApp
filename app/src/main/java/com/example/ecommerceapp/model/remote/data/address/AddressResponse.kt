package com.example.ecommerceapp.model.remote.data.address

data class AddressResponse(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)