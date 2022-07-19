package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityOrderConfirmBinding

class OrderConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orderId = intent.extras?.get("order_id")
        binding.textOrderId.text = orderId.toString()
    }
}