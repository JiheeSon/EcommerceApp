package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemCartProductBinding
import com.example.ecommerceapp.model.remote.data.CartItem
import com.example.ecommerceapp.model.remote.data.Constants

class CartAdapter(private val cartList: MutableList<CartItem>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private lateinit var binding: ItemCartProductBinding
    inner class CartViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(cartItem: CartItem) {
            binding.apply {
                textProductName.text = cartItem.productName
                textProductDescription.text = cartItem.productDescription
                val unitPrice = "$ ${cartItem.productPrice}"
                textUnitPrice.text = unitPrice
                btnNumberPicker.value = cartItem.amount
                val total = "$ ${cartItem.productPrice.toInt() * cartItem.amount}"
                textCumPrice.text = total
                Glide.with(view.context)
                    .load(Constants.BASE_IMAGE_URL + cartItem.productImage)
                    .into(imageProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCartProductBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.apply {
            bind(cartList[position])
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}