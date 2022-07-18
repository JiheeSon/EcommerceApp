package com.example.ecommerceapp.presenter.delivery

import android.util.Log
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.UserOperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.remote.data.address.AddressResponse
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler

class DeliveryPresenter(private val volleyHandler: UserVolleyHandler, private val deliveryView: DeliveryMVP.DeliveryView): DeliveryMVP.DeliveryPresenter {

    override fun loadAddressList(userId: String): String {
        deliveryView.onLoad(true)
        val message = volleyHandler.callAddressListApi( userId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    deliveryView.setResult(data as AddressResponse)
                    deliveryView.onLoad(false)
                }
                override fun onFailure(message: String) {
                    Log.i(Constants.TAG_DEV, message)
                    deliveryView.setResult(message)
                    deliveryView.onLoad(false)
                }
            }
        )
        return message
    }

    override fun addAddress(userId: String, title: String, address: String): String {
        deliveryView.onLoad(true)
        val message = volleyHandler.callAddAddressApi( userId, title, address,
            object : UserOperationalCallback {
                override fun onSuccess(status: Int, message: String) {
                    deliveryView.setResult(status, message)
                    deliveryView.onLoad(false)
                }
                override fun onFailure(message: String) {
                    Log.i(Constants.TAG_DEV, message)
                    deliveryView.setResult(message)
                    deliveryView.onLoad(false)
                }
            }
        )
        return message
    }
}