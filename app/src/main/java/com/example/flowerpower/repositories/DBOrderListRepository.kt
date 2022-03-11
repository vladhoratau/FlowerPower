package com.example.flowerpower.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.flowerpower.R
import com.example.flowerpower.data.OrdersDao
import com.example.flowerpower.models.Order
import com.example.flowerpower.utils.ApplicationClass

class DBOrderListRepository(private val ordersDao: OrdersDao) {

    val orderList: LiveData<List<Order>> = ordersDao.getAllOrders()

    companion object {
        private val TAG: String = DBOrderListRepository::class.java.canonicalName
    }

    suspend fun insert(order: Order) {
        ordersDao.insert(order)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.INSERT_ORDER))
    }

    suspend fun delete(order: Order) {
        ordersDao.delete(order)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.DELETE_ORDER))
    }

    suspend fun update(order: Order) {
        ordersDao.update(order)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.UPDATE_ORDERS))
    }

    suspend fun updateOrders(orders: List<Order>) {
        ordersDao.updateOrders(orders)
    }
}