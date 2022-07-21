package com.example.ecommerceapp.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.ecommerceapp.databinding.FragmentPaymentBinding
import com.example.ecommerceapp.model.remote.data.Constants
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_PAYMENT
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.storeCheckoutDataLocally
import com.example.ecommerceapp.view.activity.CheckoutActivity

class PaymentFragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: FragmentPaymentBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private val paymentList = arrayOf("Cash On Delivery", "Internet Banking", "Debit Card / Credit Card", "Pay Pal")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        encryptedSharedPreferences = getEncryptedPrefs(view.context)
        //encryptedSharedPreferences = requireActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE)

        setUpEvent()
        setUpOptionListener(view)
    }

    private fun setUpOptionListener(view: View) {
        binding.rgPayment.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup, checkId: Int) {
        val checkRadioButton = group.findViewById<RadioButton>(group.checkedRadioButtonId)
        val index = binding.rgPayment.indexOfChild(checkRadioButton)
        storeCheckoutDataLocally(encryptedSharedPreferences.edit(), PREF_CHECKOUT_PAYMENT, paymentList[index])
    }

    private fun setUpEvent() {
        binding.btnNext.setOnClickListener {
            //val index = binding.rgPayment.indexOfChild(binding.rgPayment.findViewById(binding.rgPayment.checkedRadioButtonId))
            //storeCheckoutDataLocally(encryptedSharedPreferences.edit(), PREF_CHECKOUT_PAYMENT, paymentList[index])
            (activity as CheckoutActivity).slideViewPager()

        }
    }
}