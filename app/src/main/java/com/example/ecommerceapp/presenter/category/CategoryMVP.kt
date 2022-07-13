package com.example.ecommerceapp.presenter.category

import com.example.ecommerceapp.model.remote.data.CategoryResponse

interface CategoryMVP {
    interface CategoryPresenter {
        fun loadCategoryList(): String
    }

    interface CategoryView {
        fun setResult(categoryResponse: CategoryResponse)
        fun onLoad(isLoading: Boolean)
    }
}