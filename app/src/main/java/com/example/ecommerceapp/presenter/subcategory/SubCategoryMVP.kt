package com.example.ecommerceapp.presenter.subcategory

import com.example.ecommerceapp.model.remote.data.SubCategoryListResponse

interface SubCategoryMVP {
    interface SubCategoryPresenter {
        fun loadSubCategoryList(categoryId: String): String
    }

    interface SubCategoryView {
        fun setResult(subCategoryListResponse: SubCategoryListResponse)
        fun onLoad(isLoading: Boolean)
    }
}