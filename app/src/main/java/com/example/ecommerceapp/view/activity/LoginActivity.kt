package com.example.ecommerceapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler
import com.example.ecommerceapp.presenter.login.LoginMVP
import com.example.ecommerceapp.presenter.login.LoginPresenter

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
        presenter = LoginPresenter(UserVolleyHandler(this), this)
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

    override fun setResult(message: String, loginSuccess: Boolean) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        if (loginSuccess) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}