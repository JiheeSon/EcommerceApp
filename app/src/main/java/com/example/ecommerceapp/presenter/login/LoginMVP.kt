package com.example.ecommerceapp.presenter.login

interface LoginMVP {
    interface LoginPresenter {
        fun loginUser(email: String, password: String): String
    }

    interface LoginView {
        fun setResult(message: String, loginSuccess: Boolean)
        fun onLoad(isLoading: Boolean)
    }
}