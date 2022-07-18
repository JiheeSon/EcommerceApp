package com.example.ecommerceapp.model.remote.data.address

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Addresse(
    val address: String,
    val address_id: String,
    val title: String
): Parcelable