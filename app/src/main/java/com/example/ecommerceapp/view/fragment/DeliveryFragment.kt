package com.example.ecommerceapp.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.DialogAddAddressBinding
import com.example.ecommerceapp.databinding.FragmentDeliveryBinding
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_ADDRESS
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_ADDRESS_TITLE
import com.example.ecommerceapp.model.remote.data.address.AddressResponse
import com.example.ecommerceapp.model.remote.data.address.Addresse
import com.example.ecommerceapp.model.remote.volleyhandler.UserVolleyHandler
import com.example.ecommerceapp.model.storage.getEncryptedPrefs
import com.example.ecommerceapp.model.storage.getLocalUserData
import com.example.ecommerceapp.model.storage.storeCheckoutDataLocally
import com.example.ecommerceapp.presenter.delivery.DeliveryMVP
import com.example.ecommerceapp.presenter.delivery.DeliveryPresenter
import com.example.ecommerceapp.view.activity.CheckoutActivity

class DeliveryFragment : Fragment(), DeliveryMVP.DeliveryView {
    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var presenter: DeliveryPresenter
    private lateinit var encryptedSharedPreferences: SharedPreferences
    //private lateinit var adapter: AddressAdapter
    private lateinit var userId: String
    private lateinit var addressList: List<Addresse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encryptedSharedPreferences = getEncryptedPrefs(view.context)
        userId = getLocalUserData(encryptedSharedPreferences).user_id.toString()
        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        presenter = DeliveryPresenter(UserVolleyHandler(view.context), this)
        presenter.loadAddressList(userId)

        binding.btnAddAddress.setOnClickListener {
            showAddDialog(view)
        }

        binding.btnNext.setOnClickListener {
            val index = binding.rgAddressOption.indexOfChild(binding.rgAddressOption.findViewById(binding.rgAddressOption.checkedRadioButtonId))
            val selected = addressList[index]
            storeCheckoutDataLocally(encryptedSharedPreferences.edit(), PREF_CHECKOUT_ADDRESS_TITLE, selected.title)
            storeCheckoutDataLocally(encryptedSharedPreferences.edit(), PREF_CHECKOUT_ADDRESS, selected.address)
            (activity as CheckoutActivity).slideViewPager()
        }
    }

    private fun showAddDialog(view: View) {
        val dialogBinding = DialogAddAddressBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(view.context).apply {
            setView(dialogBinding.root)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialogBinding.apply {
            btnAddAddressSave.setOnClickListener {
                val title = editAddressTitle.text.toString()
                val address = editAddress.text.toString()
                presenter.addAddress(userId, title, address)
                dialog.dismiss()
            }

            btnAddAddressCancel.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun setResult(addressResponse: AddressResponse) {
        addressList = addressResponse.addresses
        setUpRadioButtons()
//        adapter = AddressAdapter(addressResponse.addresses)
//        binding.recyclerViewAddress.layoutManager = LinearLayoutManager(context)
//        binding.recyclerViewAddress.adapter = adapter
//        adapter.notifyItemInserted(addressResponse.addresses.size-1)
    }

    private fun setUpRadioButtons() {
        binding.rgAddressOption.removeAllViews()
        for (address in addressList) {
            val option = RadioButton(context);

            val layoutParams: RadioGroup.LayoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(10, 10, 10, 10)
            option.layoutParams = layoutParams

            option.setButtonDrawable(R.drawable.selector_address_toggle)

            val spannableString = SpannableStringBuilder("${address.title}\n${address.address}")
            spannableString.apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, address.title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(1.5f), 0, address.title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            option.text = spannableString
            binding.rgAddressOption.addView(option)
        }
    }

    override fun setResult(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setResult(status: Int, message: String) {
        if (status == 0) {
            presenter.loadAddressList(userId)
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