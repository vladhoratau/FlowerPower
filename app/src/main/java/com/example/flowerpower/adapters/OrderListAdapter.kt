package com.example.flowerpower.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerpower.R
import com.example.flowerpower.databinding.AdapterOrderlistBinding
import com.example.flowerpower.models.Order
import com.example.flowerpower.utils.ApplicationClass

class OrderListAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {

    private var orders = mutableListOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterOrderlistBinding.inflate(inflater, parent, false)
        return OrderListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.orderID.text = ApplicationClass.instance.getString(R.string.ORDER_ID_TEXT) + order.orderID
        holder.binding.orderStatus.text = ApplicationClass.instance.getString(R.string.ORDER_STATUS_TEXT) + order.status
    }


    override fun getItemCount(): Int {
        return orders.size
    }

    fun setOrderList(orders: List<Order>) {
        this.orders = orders.toMutableList()
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(order: Order)
    }

    inner class OrderListViewHolder(val binding: AdapterOrderlistBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(orders[position])
            }
        }
    }
}