package com.example.ecommerceapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        binding.navView.setNavigationItemSelectedListener { menuItems ->
            //menuItems.isChecked = true
            binding.drawerLayoutNav.closeDrawer(GravityCompat.START)
            when (menuItems.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_cart -> Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show()
                R.id.nav_orders -> Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
                R.id.nav_profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            }
            true
        }
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