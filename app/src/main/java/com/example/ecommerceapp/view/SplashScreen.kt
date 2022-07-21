package com.example.ecommerceapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.isLoggedIn
import com.example.ecommerceapp.view.activity.LoginActivity
import com.example.ecommerceapp.view.activity.MainActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var encryptedSharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            encryptedSharedPrefs = getEncryptedPrefs(this)
            verifyLogin()
            finish()
        }, 2000)

    }

    private fun verifyLogin() {
        if (isLoggedIn(encryptedSharedPrefs)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}