package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityCheckoutBinding
import com.example.ecommerceapp.view.adapter.CheckoutViewPagerAdapter
import com.example.ecommerceapp.view.adapter.SubCategoryViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private var tabList = arrayOf("Cart Items", "Delivery", "Payment", "Summary")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tabLayoutCheckout, binding.viewPagerCheckout) {
                tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    private fun setUpViewPager() {
        val adapter = CheckoutViewPagerAdapter(this)
        binding.viewPagerCheckout.adapter = adapter
    }
}