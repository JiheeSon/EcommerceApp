package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemCartProductBinding
import com.example.ecommerceapp.model.remote.data.CartItem
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.removeCartItemLocally
import com.example.ecommerceapp.model.storage.updateCartItemLocally
import com.example.ecommerceapp.view.fragment.CartFragment

class CartAdapter(private var cartList: MutableList<CartItem>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
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
                    .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .fallback(R.drawable.ic_baseline_image_not_supported_24)
                    .into(imageProduct)

                btnNumberPicker.setValueChangedListener { value, action ->
                    val encryptedSharedPreferences = getEncryptedPrefs(view.context)

                    if (value == 0) {
                        val builder = AlertDialog.Builder(view.context)
                            .setTitle("Wait!")
                            .setIcon(R.drawable.ic_baseline_shopping_cart_24)
                            .setMessage("Want to remove this item from the cart?")
                            .setPositiveButton("Confirm") {_, _ ->
                                removeCartItemLocally(encryptedSharedPreferences, cartItem.productId)
                                val idx = cartList.indexOf(cartItem)
                                cartList.remove(cartItem)
                                notifyItemRemoved(idx)
                            }
                            .setNeutralButton("Cancel") {_, _ -> binding.btnNumberPicker.value = 1}
                        val alertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()
                    } else {
                        val copy = cartItem
                        copy.amount = value
                        updateCartItemLocally(encryptedSharedPreferences, copy)
                    }
                }
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