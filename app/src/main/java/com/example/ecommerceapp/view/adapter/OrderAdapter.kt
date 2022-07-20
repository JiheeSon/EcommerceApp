package com.example.ecommerceapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemOrderBinding
import com.example.ecommerceapp.model.remote.data.orderList.Order
import com.example.ecommerceapp.view.activity.OrderConfirmActivity

class OrderAdapter(private val orderList: List<Order>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private lateinit var binding: ItemOrderBinding
    inner class OrderViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(order: Order) {
            binding.apply {
                val orderId = "Order #${order.order_id}"
                textOrderId.text = orderId
                textAddress.text = order.address_title
                textBill.text = order.bill_amount
                textStatus.text = order.order_status
                textDate.text = order.order_date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.apply {
            val order = orderList[position]
            bind(order)
            itemView.setOnClickListener {
                val intent = Intent(view.context, OrderConfirmActivity::class.java)
                intent.putExtra("order_id", order.order_id)
                view.context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}