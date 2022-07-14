package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityProductDetailBinding
import com.example.ecommerceapp.model.remote.data.Constants.PRODUCT_ID

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.extras?.get(PRODUCT_ID)
        binding.textProductName.text = productId.toString()
    }
}