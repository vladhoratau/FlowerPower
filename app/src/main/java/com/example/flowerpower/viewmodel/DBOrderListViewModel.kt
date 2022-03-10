package com.example.flowerpower.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerpower.R
import com.example.flowerpower.data.OrderDatabase
import com.example.flowerpower.models.Order
import com.example.flowerpower.repositories.DBOrderListRepository
import com.example.flowerpower.utils.ApplicationClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBOrderListViewModel(application: Application) : ViewModel() {
    private val orderList: LiveData<List<Order>>
    private val repository: DBOrderListRepository

    companion object {
        private val TAG: String = DBOrderListViewModel::class.java.canonicalName
    }

    init {
        val dao = OrderDatabase.getDatabase(application).getOrdersDao()
        repository = DBOrderListRepository(dao)
        orderList = repository.orderList
    }

    fun insertOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(order)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.INSERT_ORDER))
    }

    fun updateOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(order)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.UPDATE_ORDER))
    }

    fun deleteOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(order)
        Log.d(TAG, ApplicationClass.instance.getString(R.string.DELETE_ORDER))
    }

    fun getAllDBOrders(): LiveData<List<Order>> {
        Log.d(TAG, ApplicationClass.instance.getString(R.string.GET_ORDERS))
        return orderList
    }
}