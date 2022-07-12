package com.example.ecommerceapp.presenter.registration

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.User
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler

class RegistrationPresenter
    (private val volleyHandler: UserVolleyHandler, private val registrationView: RegistrationMVP.RegistrationView)
    : RegistrationMVP.RegistrationPresenter {

    override fun registerUser(user: User): String {
        registrationView.onLoad(true)
        val message = volleyHandler.callRegistrationApi(user,
            object : OperationalCallback {
                override fun onSuccess(message: String) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }

                override fun onFailure(message: String) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }

            })
        return message
    }

}