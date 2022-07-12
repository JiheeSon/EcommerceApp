package com.example.ecommerceapp.presenter.registration

import com.example.ecommerceapp.model.remote.data.User

interface RegistrationMVP {
    interface RegistrationPresenter {
        fun registerUser(user: User): String
    }

    interface RegistrationView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}