package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.SUB_CATEGORY_LIST_END_POINT
import com.example.ecommerceapp.model.remote.data.SubCategoryListResponse
import com.google.gson.Gson

class SubCategoryVolleyHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callSubCategoryListApi(value_of_category_id: String, callback: OperationalCallback): String {
        val url = BASE_URL + SUB_CATEGORY_LIST_END_POINT + value_of_category_id
        var message: String? = null
        val gson = Gson()

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()
                val subCategoryListResponse = gson.fromJson(message, SubCategoryListResponse::class.java)
                callback.onSuccess(subCategoryListResponse)
            }) {
            message = it.toString()
            callback.onFailure(it.toString())
        }

        requestQueue.add(request)
        return message.toString()
    }
}