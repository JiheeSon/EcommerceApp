package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityOrderConfirmBinding
import com.example.ecommerceapp.model.remote.data.orderdetail.Order
import com.example.ecommerceapp.model.remote.data.orderdetail.OrderDetailResponse
import com.example.ecommerceapp.model.remote.volleyhandler.OrderVolleyHandler
import com.example.ecommerceapp.presenter.orderdetail.OrderDetailMVP
import com.example.ecommerceapp.presenter.orderdetail.OrderDetailPresenter
import com.example.ecommerceapp.view.adapter.OrderDetailItemAdapter

class OrderConfirmActivity : AppCompatActivity(), OrderDetailMVP.OrderDetailView {
    private lateinit var binding: ActivityOrderConfirmBinding
    private lateinit var orderId: String
    private lateinit var presenter: OrderDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orderId = intent.extras?.get("order_id").toString()
        presenter = OrderDetailPresenter(OrderVolleyHandler(this), this)
        presenter.loadOrderDetail(orderId)
    }

    override fun setResult(orderDetailResponse: OrderDetailResponse) {
        setUpViews(orderDetailResponse.order)
    }

    private fun setUpViews(order: Order) {
        val adapter = OrderDetailItemAdapter(order.items)
        binding.apply {
            textOrderId.text = order.order_id
            textOrderStatus.text = order.order_status
            textTotalBillAmount.text = order.bill_amount
            textAddressName.text = order.address_title
            textAddress.text = order.address
            textPaymentOption.text = order.payment_method

            recyclerViewProduct.layoutManager = LinearLayoutManager(baseContext)
            recyclerViewProduct.adapter = adapter
        }
    }

    override fun setResult(message: String) {
        val a = 1
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}