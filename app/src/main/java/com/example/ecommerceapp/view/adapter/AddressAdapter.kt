package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemDeliveryAddressBinding
import com.example.ecommerceapp.model.remote.data.address.Addresse


class AddressAdapter(private val addressList: List<Addresse>): RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    private lateinit var binding: ItemDeliveryAddressBinding
    private var selectedPos = -1
    private var lastPos = -1

    inner class AddressViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                selectedPos = adapterPosition
                if (lastPos == -1) {
                    lastPos = selectedPos
                } else {
                    notifyItemChanged(lastPos)
                    lastPos = selectedPos
                }
                notifyItemChanged(selectedPos)
            }
        }

        fun bind(address: Addresse) {
            binding.apply {
                textAddressName.text = address.title
                textAddress.text = address.address
            }
        }

        fun defaultBg() {
            binding.btnSelected.background = view.context.getDrawable(R.drawable.ic_baseline_radio_button_unchecked_24)
        }

        fun selectedBg() {
            binding.btnSelected.background = view.context.getDrawable(R.drawable.ic_baseline_check_circle_outline_24)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemDeliveryAddressBinding.inflate(layoutInflater, parent, false)
        return AddressViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        if(position == selectedPos)
            holder.selectedBg()
        else
            holder.defaultBg()
        holder.bind(addressList[position])
    }

    override fun getItemCount(): Int {
        return addressList.size
    }
}