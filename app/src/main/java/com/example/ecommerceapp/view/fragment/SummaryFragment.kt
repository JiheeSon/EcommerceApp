package com.example.ecommerceapp.view.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.FragmentSummaryBinding
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.remote.data.Constants.TAG_DEV
import com.example.ecommerceapp.model.remote.data.order.DeliveryAddress
import com.example.ecommerceapp.model.remote.data.order.Item
import com.example.ecommerceapp.model.remote.data.order.OrderInput
import com.example.ecommerceapp.model.remote.data.order.OrderResponse
import com.example.ecommerceapp.model.remote.volleyhandler.OrderVolleyHandler
import com.example.ecommerceapp.model.storage.*
import com.example.ecommerceapp.presenter.order.OrderMVP
import com.example.ecommerceapp.presenter.order.OrderPresenter
import com.example.ecommerceapp.view.activity.OrderConfirmActivity
import com.example.ecommerceapp.view.adapter.CartItemAdapter
import com.google.gson.Gson
import org.json.JSONObject

class SummaryFragment : Fragment(), OrderMVP.OrderView {
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var presenter: OrderPresenter
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private lateinit var orderInput: OrderInput

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encryptedSharedPreferences = getEncryptedPrefs(view.context)

        setUpViews(view)
        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        binding.btnConfirm.setOnClickListener {
            presenter = OrderPresenter(OrderVolleyHandler(view.context), this)
            presenter.placeOrder(orderInput)
        }
    }

    private fun setUpViews(view: View) {
        val itemList = getCartItemLocally(encryptedSharedPreferences)
        val orderDetail = getSummaryDataLocally(encryptedSharedPreferences)
        val userId = getLocalUserData(encryptedSharedPreferences).user_id?.toInt()

        val adapter = CartItemAdapter(itemList!!)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewProduct.adapter = adapter

        var totalBill = 0
        for (item in itemList) {
            totalBill += item.amount * item.productPrice.toInt()
        }
        binding.textPrice.text = "$ $totalBill"

        binding.textAddressName.text = orderDetail[0]
        binding.textAddress.text = orderDetail[1]
        binding.textPaymentOption.text = orderDetail[2]

        val items = ArrayList<Item>()
        for (cart in itemList) {
            items.add(Item(
                cart.productId.toInt(),
                cart.amount,
                cart.productPrice.toInt()
            ))
        }

        orderInput = OrderInput(
            userId!!,
            DeliveryAddress(orderDetail[1]!!, orderDetail[0]!!),
            items,
            totalBill,
            orderDetail[2]!!
        )
    }

    override fun setResult(orderResponse: OrderResponse) {
        removedCartDataLocally(encryptedSharedPreferences)
        val intent = Intent(context, OrderConfirmActivity::class.java)
        intent.putExtra("order_id", orderResponse.order_id)
        startActivity(intent)
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