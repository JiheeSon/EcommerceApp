package com.example.ecommerceapp.presenter.order

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.order.OrderInput
import com.example.ecommerceapp.model.remote.data.order.OrderResponse
import com.example.ecommerceapp.model.remote.volleyhandler.OrderVolleyHandler

class OrderPresenter (private val volleyHandler: OrderVolleyHandler, private val orderView: OrderMVP.OrderView): OrderMVP.OrderPresenter {
    override fun placeOrder(orderInput: OrderInput): String {
        orderView.onLoad(true)
        val message = volleyHandler.callPlaceOrderApi(orderInput,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    orderView.setResult(data as OrderResponse)
                    orderView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    orderView.setResult(message)
                    orderView.onLoad(false)
                }

            })
        return message
    }
}