package com.example.flowerpower.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerpower.databinding.AdapterOrderlistBinding
import com.example.flowerpower.models.Order

class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {

    var orders = mutableListOf<Order>()
    fun setOrderList(orders: List<Order>) {
        this.orders = orders.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterOrderlistBinding.inflate(inflater, parent, false)
        return OrderListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.orderID.text = "OrderID: #" + order.orderID
        holder.binding.orderDescription.text = "Description: " + order.description
        holder.binding.orderPrice.text = "Price: " + order.price.toString()
        holder.binding.deliverTo.text = "Deliver to: " + order.deliverTo
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class OrderListViewHolder(val binding: AdapterOrderlistBinding) :
        RecyclerView.ViewHolder(binding.root)
}