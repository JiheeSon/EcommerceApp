package com.example.ecommerceapp.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceapp.model.remote.data.CartItem
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.view.fragment.CartItemsFragment
import com.example.ecommerceapp.view.fragment.DeliveryFragment
import com.example.ecommerceapp.view.fragment.PaymentFragment
import com.example.ecommerceapp.view.fragment.SummaryFragment

class CheckoutViewPagerAdapter(fragmentActivity: FragmentActivity, private val bundleData: Any): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CartItemsFragment()
            1 -> DeliveryFragment()
            2 -> PaymentFragment()
            3 -> SummaryFragment()
            else -> CartItemsFragment()
        }
    }
}