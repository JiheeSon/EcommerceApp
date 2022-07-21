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
import com.example.ecommerceapp.model.remote.UserOperationalCallback
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.remote.data.Constants.ADDRESS_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.ADDRESS_LIST_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.BASE_URL
import com.example.ecommerceapp.model.remote.data.Constants.LOGIN_END_POINT
import com.example.ecommerceapp.model.remote.data.Constants.TAG_DEV
import com.example.ecommerceapp.model.remote.data.User
import com.example.ecommerceapp.model.remote.data.address.AddressResponse
import com.example.ecommerceapp.model.remote.data.product.ProductDetailResponse
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.storeLoginDataLocally
import com.google.gson.Gson
import org.json.JSONObject

class UserVolleyHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private var editor = getEncryptedPrefs(context).edit()

    fun callRegistrationApi(user: User, callback: UserOperationalCallback): String {
        val url = Constants.BASE_URL + Constants.REGISTRATION_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("full_name", user.full_name)
        data.put("mobile_no", user.mobile_no)
        data.put("email_id", user.email_id)
        data.put("password", user.password)

        val request = JsonObjectRequest( Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                val status = response.getInt("status")
                Log.i(Constants.TAG_DEV, message.toString())
                callback.onSuccess(status, message.toString())

            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i(Constants.TAG_DEV, error.printStackTrace().toString())
                callback.onFailure(message.toString())
            })
        requestQueue.add(request)
        return message.toString()
    }

    fun callLoginApi(email: String, password: String, callback: UserOperationalCallback): String {
        val url = BASE_URL + LOGIN_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("email_id", email)
        data.put("password", password)

        val request = JsonObjectRequest(
            Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                val status = response.getInt("status")
                if (status == 0) {
                    val user = response.getJSONObject("user")
                    storeLoginDataLocally(
                        editor,
                        user.getString("user_id"),
                        user.getString("full_name"),
                        user.getString("mobile_no"),
                        user.getString("email_id")
                    )
                    val userObject = User(
                        user.getString("user_id"),
                        user.getString("full_name"),
                        user.getString("mobile_no"),
                        user.getString("email_id"),
                        password
                    )
                }
                callback.onSuccess(status, message.toString())
            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i(Constants.TAG_DEV, error.printStackTrace().toString())
                callback.onFailure(message.toString())
            })

        requestQueue.add(request)
        return message.toString()
    }

    fun callAddressListApi(userId: String, callback: OperationalCallback): String {
        val url = BASE_URL + ADDRESS_LIST_END_POINT + userId
        var message: String? = null
        val gson = Gson()

        Log.i(TAG_DEV, "user id is $userId")

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()
                val addressResponse = gson.fromJson(message, AddressResponse::class.java)
                Log.i(TAG_DEV, message!!)
                callback.onSuccess(addressResponse)
            }
        ) {
            message = it.toString()
            Log.i(TAG_DEV, message!!)
            callback.onFailure(message.toString())
        }
        requestQueue.add(request)
        return message.toString()
    }

    fun callAddAddressApi(userId: String, title: String, address: String, callback: UserOperationalCallback): String {
        val url = BASE_URL + ADDRESS_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("user_id", userId)
        data.put("title", title)
        data.put("address", address)

        val request = JsonObjectRequest( Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                val status = response.getInt("status")
                callback.onSuccess(status, message.toString())
            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i(TAG_DEV, error.printStackTrace().toString())
                callback.onFailure(message.toString())
            })

        requestQueue.add(request)
        return message.toString()
    }
}