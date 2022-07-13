package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.CategoryResponse
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.CATEGORY_END_POINT
import com.google.gson.Gson

class CategoryVolleyHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)
    var message: String? = null
    val gson = Gson()

    fun callProductCategoryApi(callback: OperationalCallback): String {
        val url = BASE_URL + CATEGORY_END_POINT

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()
                val categoryResponse = gson.fromJson(message, CategoryResponse::class.java)
                callback.onSuccess(categoryResponse)
            }) {
            message = it.toString()
            callback.onFailure(it.toString())
        }

        requestQueue.add(request)
        return message.toString()
    }
}