package com.example.ecommerceapp.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentOrdersBinding
import com.example.ecommerceapp.model.remote.data.orderList.OrderListResponse
import com.example.ecommerceapp.model.remote.volleyhandler.OrderVolleyHandler
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.getLocalUserData
import com.example.ecommerceapp.presenter.orderList.OrderListMVP
import com.example.ecommerceapp.presenter.orderList.OrderListPresenter
import com.example.ecommerceapp.view.adapter.OrderAdapter

class OrdersFragment : Fragment(), OrderListMVP.OrderListView {
    private lateinit var binding: FragmentOrdersBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private lateinit var userId: String
    private lateinit var presenter: OrderListPresenter
    private lateinit var orderList: List<OrderListResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encryptedSharedPreferences = getEncryptedPrefs(view.context)
        userId = getLocalUserData(encryptedSharedPreferences).user_id.toString()

        setUpViews(view)
    }

    private fun setUpViews(view: View) {
        presenter = OrderListPresenter(OrderVolleyHandler(view.context), this)
        presenter.getAllOrders(userId)
    }

    override fun setResult(orderListResponse: OrderListResponse) {
        if (orderListResponse.orders.isEmpty()) {
            binding.noOrders.visibility = View.VISIBLE
        } else {
            val adapter = OrderAdapter(orderListResponse.orders)
            binding.recyclerViewOrders.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewOrders.adapter = adapter
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