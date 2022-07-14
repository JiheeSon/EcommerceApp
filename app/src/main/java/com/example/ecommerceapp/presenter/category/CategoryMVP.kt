package com.example.ecommerceapp.presenter.category

import com.example.ecommerceapp.model.remote.data.category.CategoryResponse

interface CategoryMVP {
    interface CategoryPresenter {
        fun loadCategoryList(): String
    }

    interface CategoryView {
        fun setResult(categoryResponse: CategoryResponse)
        fun onLoad(isLoading: Boolean)
    }
}