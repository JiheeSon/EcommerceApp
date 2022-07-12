package com.example.ecommerceapp.presenter.login

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler

class LoginPresenter
    (private val volleyHandler: UserVolleyHandler, private val loginView: LoginMVP.LoginView)
    : LoginMVP.LoginPresenter {

    override fun loginUser(email: String, password: String): String {
        loginView.onLoad(true)
        val message = volleyHandler.callLoginApi(email, password,
            object : OperationalCallback {
                override fun onSuccess(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(message, true)
                }

                override fun onFailure(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(message, false)
                }

            })

        return message
    }

}