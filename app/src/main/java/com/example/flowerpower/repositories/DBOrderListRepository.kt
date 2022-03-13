package com.example.flowerpower.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.flowerpower.R
import com.example.flowerpower.data.OrdersDao
import com.example.flowerpower.models.Order
import com.example.flowerpower.models.Status
import com.example.flowerpower.utils.ApplicationClass

class DBOrderListRepository(private val ordersDao: OrdersDao) {

    companion object {
        private val TAG: String? = DBOrderListRepository::class.java.canonicalName
    }

    val orderList: LiveData<List<Order>> = ordersDao.getAllOrders()

    suspend fun updateStatus(orderID: String, status: Status) {
        ordersDao.updateStatus(orderID, status.toString())
    }

    suspend fun updateOrders(orders: List<Order>) {
        for (order in orders) {
            this.update(order)
        }
    }

    private suspend fun update(order: Order) {
        val result = ordersDao.insert(order)
        if (result == -1L) {
            ordersDao.update(order.orderID, order.description, order.price, order.deliverTo)
        }
        Log.d(TAG, ApplicationClass.instance.getString(R.string.UPDATE_ORDERS))
    }
}