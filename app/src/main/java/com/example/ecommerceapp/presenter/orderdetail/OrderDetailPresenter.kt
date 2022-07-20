package com.example.ecommerceapp.presenter.orderdetail

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.orderdetail.OrderDetailResponse
import com.example.ecommerceapp.model.remote.volleyhandler.OrderVolleyHandler

class OrderDetailPresenter (private val volleyHandler: OrderVolleyHandler, private val orderDetailView: OrderDetailMVP.OrderDetailView): OrderDetailMVP.OrderDetailPresenter {
    override fun loadOrderDetail(orderId: String): String {
        orderDetailView.onLoad(true)
        val message = volleyHandler.callOrderDetailApi(orderId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    orderDetailView.setResult(data as OrderDetailResponse)
                    orderDetailView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    orderDetailView.setResult(message)
                    orderDetailView.onLoad(false)
                }
            })
        return message

    }
}