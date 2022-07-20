package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.PRODUCT_DETAIL_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.SEARCH_END_POINT
import com.example.ecommerceapp.model.remote.data.product.ProductDetailResponse
import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryProductListResponse
import com.google.gson.Gson

class ProductVolleyHandler(private val context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callProductDetailApiCall(product_id: String, callback: OperationalCallback): String {
        val url = BASE_URL + PRODUCT_DETAIL_END_POINT + product_id
        var message: String? = null
        val gson = Gson()

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()
                val productDetailResponse = gson.fromJson(message, ProductDetailResponse::class.java)
                callback.onSuccess(productDetailResponse)
            }
        ) {
            message = it.toString()
            callback.onFailure(message.toString())
        }
        requestQueue.add(request)
        return message.toString()
    }

    fun callSearchApi(query: String, callback: OperationalCallback): String {
        val url = BASE_URL + SEARCH_END_POINT + query
        var message: String? = null
        val gson = Gson()

        val request = object: StringRequest(Request.Method.GET, url,
            Response.Listener {
                val productsResponse = gson.fromJson(it.toString(), SubCategoryProductListResponse::class.java)
                callback.onSuccess(productsResponse)
            }, {
                message = it.toString()
                callback.onFailure(it.toString())
            }){
        }

        requestQueue.add(request)
        return message.toString()
    }

}