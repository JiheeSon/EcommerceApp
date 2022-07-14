package com.example.ecommerceapp.presenter.productdetail

interface ProductDetailMVP {
    interface ProductDetailPresenter {
        fun getProductDetail(productId: String): String
    }

    interface ProductDetailView {
        fun setResult(data: Any, successed: Boolean)
        fun onLoad(isLoading: Boolean)
    }
}