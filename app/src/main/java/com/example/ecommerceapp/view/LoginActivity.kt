package com.example.ecommerceapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.example.ecommerceapp.model.remote.data.User
import com.example.ecommerceapp.model.remote.volleyhandler.LoginVolleyHandler
import com.example.ecommerceapp.presenter.login.LoginMVP
import com.example.ecommerceapp.presenter.login.LoginPresenter
import com.example.ecommerceapp.presenter.registration.RegistrationMVP

class LoginActivity : AppCompatActivity(), LoginMVP.LoginView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpEvents()
    }

    private fun setUpEvents() {
        presenter = LoginPresenter(LoginVolleyHandler(this), this)
        binding.apply {
            btnLogin.setOnClickListener {
                val email = editEmailId.text.toString()
                val password = editPassword.text.toString()
                presenter.loginUser(email, password)
            }
        }
    }

    private fun setUpViews() {
        binding.textNoAccount.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
//        binding.textForgotPassword.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//        }
    }

    override fun setResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
    }
}