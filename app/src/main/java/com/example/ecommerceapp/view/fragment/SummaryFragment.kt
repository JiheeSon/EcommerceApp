package com.example.ecommerceapp.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSummaryBinding
import com.example.ecommerceapp.model.remote.data.address.Addresse
import com.example.ecommerceapp.model.storage.getEncryptedPrefs

class SummaryFragment : Fragment() {
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encryptedSharedPreferences = getEncryptedPrefs(view.context)
    }
}