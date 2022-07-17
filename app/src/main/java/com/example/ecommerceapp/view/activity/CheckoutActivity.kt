package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityCheckoutBinding
import com.example.ecommerceapp.model.remote.data.CartItem
import com.example.ecommerceapp.view.adapter.CheckoutViewPagerAdapter
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
//        for (tab in tabList) {
//            binding.tabLayoutCheckout.addTab(binding.tabLayoutCheckout.newTab().setText(tab))
//            binding.tabLayoutCheckout.isClickable = false
//        }
//        binding.tabLayoutCheckout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when (tab?.position) {
//                    0 -> replaceFragment(R.id.framelayout_checkout, CartItemsFragment())
//                    1 -> replaceFragment(R.id.framelayout_checkout, DeliveryFragment())
//                    2 -> replaceFragment(R.id.framelayout_checkout, PaymentFragment())
//                    3 -> replaceFragment(R.id.framelayout_checkout, SummaryFragment())
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//        })
        TabLayoutMediator(binding.tabLayoutCheckout, binding.viewPagerCheckout) {
                tab, position ->
            tab.text = tabList[position]
            tab.view.isClickable = false
        }.attach()
    }

    private fun setUpViewPager() {
        val adapter = CheckoutViewPagerAdapter(this, "")
        binding.viewPagerCheckout.adapter = adapter
        binding.viewPagerCheckout.isUserInputEnabled = false
    }

    fun slideViewPager() {
        findViewById<ViewPager2>(R.id.view_pager_checkout).currentItem = findViewById<ViewPager2>(R.id.view_pager_checkout).currentItem + 1
    }
}