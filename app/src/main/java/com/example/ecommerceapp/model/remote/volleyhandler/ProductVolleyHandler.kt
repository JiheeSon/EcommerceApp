package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.PRODUCT_DETAIL_END_POINT
import com.google.gson.Gson

class ProductVolleyHandler(private val context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callProductDetailApiCall(product_id: String): String {
        val url = BASE_URL + PRODUCT_DETAIL_END_POINT + product_id
        var message: String? = null
        val gson = Gson()

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()

            }, {

            }
        )

        return message.toString()
    }
}