package com.example.ecommerceapp.model.remote.data.subcategory

import com.example.ecommerceapp.model.remote.data.subcategory.Product

data class SubCategoryProductListResponse(
    val message: String,
    val products: ArrayList<Product>,
    val status: Int
)