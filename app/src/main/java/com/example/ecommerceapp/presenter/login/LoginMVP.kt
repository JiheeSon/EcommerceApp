package com.example.ecommerceapp.presenter.login

import org.json.JSONObject

interface LoginMVP {
    interface LoginPresenter {
        fun loginUser(email: String, password: String): JSONObject
    }

    interface LoginView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}