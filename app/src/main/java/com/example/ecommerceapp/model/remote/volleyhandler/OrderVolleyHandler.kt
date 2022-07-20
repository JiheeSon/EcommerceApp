package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.ORDER_LIST_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.ORDER_PLACE_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.TAG_DEV
import com.example.ecommerceapp.model.remote.data.order.OrderInput
import com.example.ecommerceapp.model.remote.data.order.OrderResponse
import com.example.ecommerceapp.model.remote.data.orderList.OrderListResponse
import com.example.ecommerceapp.model.remote.data.product.ProductDetailResponse
import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryListResponse
import com.google.gson.Gson
import org.json.JSONObject

class OrderVolleyHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callPlaceOrderApi(orderInput: OrderInput, callback: OperationalCallback): String {
        val url = BASE_URL + ORDER_PLACE_END_POINT
        val gson = Gson()
        val data = JSONObject(gson.toJson(orderInput))
        var message: String? = null

        val request = JsonObjectRequest( Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                val orderResponse = gson.fromJson(response.toString(), OrderResponse::class.java)
                callback.onSuccess(orderResponse)
            }, { error: VolleyError ->
                Log.i(TAG_DEV, error.printStackTrace().toString())
                error.printStackTrace()
                callback.onFailure(error.printStackTrace().toString())
            })

        requestQueue.add(request)
        return message.toString()
    }

    fun callOrderListApi(userId: String, callback: OperationalCallback): String {
        val url = BASE_URL + ORDER_LIST_END_POINT + userId
        var message: String? = null
        val gson = Gson()

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()
                val orderListResponse = gson.fromJson(message, OrderListResponse::class.java)
                callback.onSuccess(orderListResponse)
            }) {
            message = it.toString()
            callback.onFailure(it.toString())
        }

        requestQueue.add(request)
        return message.toString()
    }
}