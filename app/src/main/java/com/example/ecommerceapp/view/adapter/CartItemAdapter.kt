package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemCheckoutCartItemsBinding
import com.example.ecommerceapp.model.remote.data.CartItem
import com.example.ecommerceapp.model.remote.data.Constants

class CartItemAdapter(private val itemList: MutableList<CartItem>): RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>(){
    private lateinit var binding: ItemCheckoutCartItemsBinding

    inner class CartItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(cartItem: CartItem) {
            binding.apply {
                textProductName.text = cartItem.productName
                val unitPrice = "$ ${cartItem.productPrice}"
                textUnitPrice.text = unitPrice
                val total = "$ ${cartItem.productPrice.toInt() * cartItem.amount}"
                textAmount.text = total
                textQuantity.text = cartItem.amount.toString()

                Glide.with(view.context)
                    .load(Constants.BASE_IMAGE_URL + cartItem.productImage)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .fallback(R.drawable.ic_baseline_image_not_supported_24)
                    .into(imageProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCheckoutCartItemsBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}