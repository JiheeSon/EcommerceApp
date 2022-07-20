package com.example.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemCheckoutCartItemsBinding
import com.example.ecommerceapp.databinding.ItemOrderBinding
import com.example.ecommerceapp.model.remote.data.orderdetail.Item

class OrderDetailItemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<OrderDetailItemAdapter.ItemViewHolder>() {
    private lateinit var binding: ItemCheckoutCartItemsBinding

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item: Item) {
            binding.apply {
                textProductName.text = item.product_name
                textAmount.text = item.amount
                textQuantity.text = item.quantity
                textUnitPrice.text = item.unit_price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCheckoutCartItemsBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}