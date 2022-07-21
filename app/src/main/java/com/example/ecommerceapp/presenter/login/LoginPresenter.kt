package com.example.ecommerceapp.presenter.login

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.UserOperationalCallback
import com.example.ecommerceapp.model.remote.data.User
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler

class LoginPresenter
    (private val volleyHandler: UserVolleyHandler, private val loginView: LoginMVP.LoginView)
    : LoginMVP.LoginPresenter {

    override fun loginUser(email: String, password: String): String {
        loginView.onLoad(true)
        val message = volleyHandler.callLoginApi(email, password,
            object : UserOperationalCallback {
                override fun onSuccess(status: Int, message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(status, message)
                }

                override fun onFailure(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(-1, message)
                }

            })

        return message
    }

}