package com.example.ecommerceapp.model.remote.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    val productId: String,
    val productName: String,
    val productDescription: String,
    val productPrice: String,
    val productImage: String,
    val amount: Int
): Parcelable
