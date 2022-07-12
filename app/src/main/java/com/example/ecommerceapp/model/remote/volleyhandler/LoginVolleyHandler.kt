package com.example.ecommerceapp.model.remote.volleyhandler

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.LOGIN_END_POINT
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.storeLoginDataLocally
import org.json.JSONObject

class LoginVolleyHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private var editor = getEncryptedPrefs(context).edit()

    fun callLoginApi(email: String, password: String, callback: OperationalCallback): JSONObject {
        val url = BASE_URL + LOGIN_END_POINT
        val data = JSONObject()
        var result = JSONObject()
        var message: String? = null

        data.put("email_id", email)
        data.put("password", password)

        val request = JsonObjectRequest(Request.Method.POST, url, data,
            { response: JSONObject ->
                result = response
                message = response.getString("message")
                callback.onSuccess(message.toString())
                val user =  result.getJSONObject("user")
                storeLoginDataLocally(editor, user.getString("user_id"), user.getString("full_name"), user.getString("mobile_no"), user.getString("user_id"))
            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i(Constants.TAG_DEV, error.printStackTrace().toString())
                callback.onFailure(message.toString())
            })

        requestQueue.add(request)
        return result
    }
}