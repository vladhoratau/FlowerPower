package com.example.flowerpower.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.flowerpower.R
import com.example.flowerpower.data.OrdersDao
import com.example.flowerpower.models.Order
import com.example.flowerpower.models.Status
import com.example.flowerpower.utils.ApplicationClass

class DBOrdersRepository(private val ordersDao: OrdersDao) {

    companion object {
        private val TAG: String? = DBOrdersRepository::class.java.canonicalName
    }

    val orders: LiveData<List<Order>> = ordersDao.getOrders()

    suspend fun updateOrderStatus(orderID: String, status: Status) {
        ordersDao.updateOrderStatus(orderID, status.toString())
    }

    suspend fun updateOrders(orders: List<Order>) {
        for (order in orders) {
            this.updateOrder(order)
        }
    }

    private suspend fun updateOrder(order: Order) {
        val result = ordersDao.insertOrder(order)
        if (result == -1L) {
            ordersDao.updateOrder(order.orderID, order.description, order.price, order.deliverTo)
        }
        Log.d(TAG, ApplicationClass.instance.getString(R.string.UPDATE_ORDERS))
    }
}