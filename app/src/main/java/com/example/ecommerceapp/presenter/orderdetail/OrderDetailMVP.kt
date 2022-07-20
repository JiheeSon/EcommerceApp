package com.example.ecommerceapp.presenter.orderdetail

import com.example.ecommerceapp.model.remote.data.orderdetail.OrderDetailResponse

interface OrderDetailMVP {
    interface OrderDetailPresenter {
        fun loadOrderDetail(orderId: String): String
    }

    interface OrderDetailView {
        fun setResult(orderDetailResponse: OrderDetailResponse)
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}