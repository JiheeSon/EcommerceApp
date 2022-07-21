package com.example.ecommerceapp.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.FragmentCartItemsBinding
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.storage.getCartItemLocally
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.view.activity.CheckoutActivity
import com.example.ecommerceapp.view.adapter.CartItemAdapter

class CartItemsFragment : Fragment() {
    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encryptedSharedPreferences = getEncryptedPrefs(view.context)

        setUpViews(view)
    }

    private fun setUpViews(view: View) {
        val cartList = getCartItemLocally(encryptedSharedPreferences)

        val adapter = CartItemAdapter(cartList!!)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewProduct.adapter = adapter

        var totalBill = 0
        for (item in cartList) {
            totalBill += item.amount * item.productPrice.toInt()
        }
        binding.textTotalPrice.text = "$ $totalBill"

        binding.btnNext.setOnClickListener {
            (activity as CheckoutActivity).slideViewPager()
        }
    }
}