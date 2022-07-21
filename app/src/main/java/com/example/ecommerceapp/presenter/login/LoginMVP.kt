package com.example.ecommerceapp.presenter.login

import com.example.ecommerceapp.model.remote.data.User

interface LoginMVP {
    interface LoginPresenter {
        fun loginUser(email: String, password: String): String
    }

    interface LoginView {
        fun setResult(status: Int, message: String)
        fun onLoad(isLoading: Boolean)
    }
}