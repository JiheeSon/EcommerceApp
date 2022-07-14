package com.example.ecommerceapp.model.remote.data.subcategory

import com.example.ecommerceapp.model.remote.data.subcategory.Subcategory

data class SubCategoryListResponse(
    val message: String,
    val status: Int,
    val subcategories: ArrayList<Subcategory>
)