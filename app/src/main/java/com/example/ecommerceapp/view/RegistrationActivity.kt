package com.example.ecommerceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            }
        }
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