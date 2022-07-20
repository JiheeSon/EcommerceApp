package com.example.ecommerceapp.presenter.search

import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryProductListResponse

interface SearchMVP {
    interface SearchPresenter {
        fun searchProduct(query: String): String
    }

    interface SearchView {
        fun setResult(subCategoryProductListResponse: SubCategoryProductListResponse)
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}