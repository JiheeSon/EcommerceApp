package com.example.ecommerceapp.view.fragment

import android.content.Context
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
import com.example.ecommerceapp.model.remote.data.CartItem
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

class SummaryFragment : Fragment(), OrderMVP.OrderView {
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var presenter: OrderPresenter
    private lateinit var encryptedSharedPreferences: SharedPreferences

    private lateinit var orderInput: OrderInput
    private lateinit var orderDetail: Array<String?>
    private var itemList: MutableList<CartItem>? =  null
    private var userId: Int? = null
    private var totalBill = 0


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
        //encryptedSharedPreferences = requireActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE)

        setUpViews(view)
        setUpEvents()
    }

    override fun onResume() {
        super.onResume()
        orderDetail = getSummaryDataLocally(encryptedSharedPreferences)
        binding.textPaymentOption.text = orderDetail[2]
    }

    private fun setUpEvents() {
        binding.btnConfirm.setOnClickListener {
            prepareApiRequestData()
            presenter = OrderPresenter(OrderVolleyHandler(requireContext()), this)
            presenter.placeOrder(orderInput)
            //Log.i("jihee", orderInput.toString())
        }
    }

    private fun prepareApiRequestData() {
        val items = ArrayList<Item>()
        for (cart in itemList!!) {
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

    private fun setUpViews(view: View) {
        itemList = getCartItemLocally(encryptedSharedPreferences)
        orderDetail = getSummaryDataLocally(encryptedSharedPreferences)
        userId = getLocalUserData(encryptedSharedPreferences).user_id?.toInt()

        val adapter = CartItemAdapter(itemList!!)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewProduct.adapter = adapter

        for (item in itemList!!) {
            totalBill += item.amount * item.productPrice.toInt()
        }
        binding.textPrice.text = "$ $totalBill"

        binding.textAddressName.text = orderDetail[0]
        binding.textAddress.text = orderDetail[1]
        binding.textPaymentOption.text = orderDetail[2]
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