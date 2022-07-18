package com.example.ecommerceapp.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentPaymentBinding
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_PAYMENT
import com.example.ecommerceapp.model.remote.data.address.Addresse
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.storeCheckoutDataLocally
import com.example.ecommerceapp.view.activity.CheckoutActivity

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private val paymentList = arrayOf("Cash On Delivery", "Internet Banking", "Debit Card / Credit Card", "Pay Pal")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        encryptedSharedPreferences = getEncryptedPrefs(view.context)

        setUpEvent()
    }

    private fun setUpEvent() {
        binding.btnNext.setOnClickListener {
            Log.i("jihee", "hi")
            val index = binding.rgPayment.indexOfChild(binding.rgPayment.findViewById(binding.rgPayment.checkedRadioButtonId))
            storeCheckoutDataLocally(encryptedSharedPreferences.edit(), PREF_CHECKOUT_PAYMENT, paymentList[index])
            (activity as CheckoutActivity).slideViewPager()
        }
    }
}