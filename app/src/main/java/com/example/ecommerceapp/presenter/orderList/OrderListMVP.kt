package com.example.ecommerceapp.presenter.orderList

import com.example.ecommerceapp.model.remote.data.orderList.OrderListResponse

interface OrderListMVP {
    interface OrderListPresenter {
        fun getAllOrders(userId: String): String
    }

    interface OrderListView {
        fun setResult(orderListResponse: OrderListResponse)
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}