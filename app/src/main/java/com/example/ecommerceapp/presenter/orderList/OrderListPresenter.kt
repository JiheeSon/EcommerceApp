package com.example.ecommerceapp.presenter.orderList

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.orderList.OrderListResponse
import com.example.ecommerceapp.model.remote.volleyhandler.OrderVolleyHandler

class OrderListPresenter (private val volleyHandler: OrderVolleyHandler, private val orderListView: OrderListMVP.OrderListView): OrderListMVP.OrderListPresenter {
    override fun getAllOrders(userId: String): String {
        orderListView.onLoad(true)
        val message = volleyHandler.callOrderListApi(userId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    orderListView.setResult(data as OrderListResponse)
                    orderListView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    orderListView.setResult(message)
                    orderListView.onLoad(false)
                }

            })
        return message

    }
}