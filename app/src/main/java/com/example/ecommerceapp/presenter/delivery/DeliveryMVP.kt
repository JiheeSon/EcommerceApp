package com.example.ecommerceapp.presenter.delivery

import com.example.ecommerceapp.model.remote.data.address.AddressResponse

interface DeliveryMVP {
    interface DeliveryPresenter {
        fun loadAddressList(userId: String): String
        fun addAddress(userId: String, title: String, address: String): String
    }

    interface DeliveryView {
        fun setResult(addressResponse: AddressResponse)
        fun setResult(message: String)
        fun setResult(status: Int, message: String)
        fun onLoad(isLoading: Boolean)
    }
}