package com.example.ecommerceapp.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.model.remote.data.User
import com.example.ecommerceapp.model.storage.deleteLocalUserData
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.getLocalUserData
import com.example.ecommerceapp.view.SplashScreen
import com.example.ecommerceapp.view.fragment.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private lateinit var userInfo: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras?.get("cart") != null) {
            addFragment(R.id.framelayout, CartFragment())
        } else {
            addFragment(R.id.framelayout, HomeFragment())
        }
        getUserInfo()
        initNavBar()
    }

    private fun getUserInfo() {
        encryptedSharedPreferences = getEncryptedPrefs(this@MainActivity)
        userInfo = getLocalUserData(encryptedSharedPreferences)
    }

    private fun initNavBar() {
        //setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        binding.navDrawer.setNavigationItemSelectedListener { menuItems ->
            //menuItems.isChecked = true
            binding.drawerLayoutNav.closeDrawer(GravityCompat.START)
            when (menuItems.itemId) {
                R.id.nav_home -> replaceFragment(R.id.framelayout, HomeFragment())
                R.id.nav_cart -> replaceFragment(R.id.framelayout, CartFragment())
                R.id.nav_orders -> replaceFragment(R.id.framelayout, OrdersFragment())
                R.id.nav_profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> {
                    deleteLocalUserData(encryptedSharedPreferences)
                    startActivity(Intent(this@MainActivity, SplashScreen::class.java))
                }
            }
            true
        }

        val navigationViewHeader = binding.navDrawer.inflateHeaderView(R.layout.header_nav)
        navigationViewHeader.findViewById<TextView>(R.id.header_welcome_user).text = "Welcome ${userInfo.full_name}"
        navigationViewHeader.findViewById<TextView>(R.id.header_user_email).text = userInfo.email_id
        navigationViewHeader.findViewById<TextView>(R.id.header_user_mobile).text = userInfo.mobile_no
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayoutNav.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayoutNav.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayoutNav.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(binding.drawerLayoutNav.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayoutNav.closeDrawers()
        }else{
            super.onBackPressed()
        }
    }

}