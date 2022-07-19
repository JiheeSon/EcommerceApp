package com.example.ecommerceapp.presenter.order

import com.example.ecommerceapp.model.remote.data.order.OrderInput
import com.example.ecommerceapp.model.remote.data.order.OrderResponse

interface OrderMVP {
    interface OrderPresenter {
        fun placeOrder(orderInput: OrderInput): String
    }

    interface OrderView {
        fun setResult(orderResponse: OrderResponse)
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}