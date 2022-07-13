package com.example.ecommerceapp.model.remote.data

data class SubCategoryListResponse(
    val message: String,
    val status: Int,
    val subcategories: ArrayList<Subcategory>
)