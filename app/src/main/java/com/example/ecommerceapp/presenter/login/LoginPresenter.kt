package com.example.ecommerceapp.presenter.login

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.volleyhandler.LoginVolleyHandler
import org.json.JSONObject
import kotlin.math.log

class LoginPresenter
    (private val volleyHandler: LoginVolleyHandler, private val loginView: LoginMVP.LoginView)
    : LoginMVP.LoginPresenter {

    override fun loginUser(email: String, password: String): JSONObject {
        loginView.onLoad(true)
        val result = volleyHandler.callLoginApi(email, password,
            object : OperationalCallback {
                override fun onSuccess(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(message)
                }

                override fun onFailure(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(message)
                }

            })

        return result
    }

}