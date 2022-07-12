package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.REGISTRATION_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.TAG_DEV
import com.example.ecommerceapp.model.remote.data.User
import org.json.JSONObject

class RegistrationVolleHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callRegistrationApi(user: User, callback: OperationalCallback): String {
        var url = BASE_URL + REGISTRATION_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("full_name", user.full_name)
        data.put("mobile_no", user.mobile_no)
        data.put("email_id", user.email_id)
        data.put("password", user.password)

        val request = JsonObjectRequest( Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                Log.i(TAG_DEV, message.toString())
                callback.onSuccess(message.toString())

            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i(TAG_DEV, error.printStackTrace().toString())
                callback.onFailure(message.toString())

            })
        requestQueue.add(request)
        return message.toString()
    }
}