package com.example.ecommerceapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.example.ecommerceapp.model.local.UserDao
import com.example.ecommerceapp.model.remote.data.User
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

                clearText()
            }
        }
    }

    private fun clearText() {
        binding.apply {
            editEmailId.text = null
            editPassword.text = null
        }
    }

    private fun setUpViews() {
        binding.textNoAccount.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    override fun setResult(status: Int, message: String) {
        if (status == 0) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else {
            showFailureDialog(message)
        }
    }

    private fun showFailureDialog(message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Sorry")
            .setIcon(R.drawable.ic_baseline_shopping_cart_24)
            .setMessage(message)
            .setNeutralButton("Try Again", null)
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}