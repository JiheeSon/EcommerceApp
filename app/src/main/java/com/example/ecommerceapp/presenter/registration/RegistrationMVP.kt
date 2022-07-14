package com.example.ecommerceapp.presenter.registration

import com.example.ecommerceapp.model.remote.data.User

interface RegistrationMVP {
    interface RegistrationPresenter {
        fun registerUser(user: User): String
    }

    interface RegistrationView {
        fun setResult(status: Int, message: String)
        fun onLoad(isLoading: Boolean)
    }
}