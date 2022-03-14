package com.example.flowerpower.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerpower.R
import com.example.flowerpower.data.OrdersDatabase
import com.example.flowerpower.models.Order
import com.example.flowerpower.models.Status
import com.example.flowerpower.repositories.DBOrdersRepository
import com.example.flowerpower.utils.ApplicationClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBOrdersViewModel(application: Application) : ViewModel() {

    private val orders: LiveData<List<Order>>
    private val dbOrdersRepository: DBOrdersRepository

    companion object {
        private val TAG: String? = DBOrdersViewModel::class.java.canonicalName
    }

    init {
        val dao = OrdersDatabase.getDatabase(application).getOrdersDao()
        dbOrdersRepository = DBOrdersRepository(dao)
        orders = dbOrdersRepository.orders
    }

    fun updateOrderStatus(orderID: String, status: Status) = viewModelScope.launch(Dispatchers.IO) {
        dbOrdersRepository.updateOrderStatus(orderID, status)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.UPDATE_ORDER_STATUS))
    }

    fun updateOrders(orders: List<Order>) = viewModelScope.launch(Dispatchers.IO) {
        dbOrdersRepository.updateOrders(orders)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.UPDATE_ORDERS))
    }

    fun getOrders(): LiveData<List<Order>> {
        Log.d(TAG, ApplicationClass.instance.getString(R.string.GET_ORDERS))
        return orders
    }
}