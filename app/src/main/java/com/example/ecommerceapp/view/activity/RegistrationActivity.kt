package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityRegistrationBinding
import com.example.ecommerceapp.model.remote.data.User
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler
import com.example.ecommerceapp.presenter.registration.RegistrationMVP
import com.example.ecommerceapp.presenter.registration.RegistrationPresenter

class RegistrationActivity : AppCompatActivity(), RegistrationMVP.RegistrationView {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpEvents()
    }

    private fun setUpEvents() {
        presenter = RegistrationPresenter(UserVolleyHandler(this), this)
        binding.apply {
            btnRegister.setOnClickListener {
                val name = editName.text.toString()
                val mobile = editMobile.text.toString()
                val email = editEmailId.text.toString()
                val password = editPassword.text.toString()
                val user = User(null, name, mobile, email, password)
                presenter.registerUser(user)

                clearText()
            }

            textHaveAccount.setOnClickListener {
                finish()
            }
        }
    }

    private fun clearText() {
        binding.apply {
            editName.text = null
            editEmailId.text = null
            editMobile.text = null
            editPassword.text = null
        }
    }

    override fun setResult(status: Int, message: String) {
        val title = if (status == 0) "Welcome!" else "Sorry"
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setIcon(R.drawable.ic_baseline_shopping_cart_24)
            .setMessage(message)
            .setPositiveButton("Go to Login") {_, _ -> finish() }
            .setNeutralButton("Cancel", null)
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