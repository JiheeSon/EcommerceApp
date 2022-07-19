package com.example.ecommerceapp.view.fragment

import android.content.Context
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
import com.example.ecommerceapp.model.storage.getCartItemLocally
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.getSummaryDataLocally
import com.example.ecommerceapp.view.adapter.CartItemAdapter

class SummaryFragment : Fragment() {
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        }
    }

    private fun setUpViews(view: View) {
        val itemList = getCartItemLocally(encryptedSharedPreferences)
        val orderDetail = getSummaryDataLocally(encryptedSharedPreferences)

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

        Log.i("jihee", orderDetail.contentToString())
    }
}