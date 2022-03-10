package com.example.flowerpower.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowerpower.models.Order
import com.example.flowerpower.repositories.OrderListRepository
import com.example.flowerpower.services.OrderListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListViewModel :
    ViewModel() {
    companion object {
        private val TAG: String = OrderListViewModel::class.java.canonicalName
        const val RESPONSE_SUCCESSFUL = "Order list get request successful"
    }

    val orderList = MutableLiveData<List<Order>>()
    val orderListRepository = OrderListRepository(OrderListService.getInstance())

    fun getOrderList() {
        val response = orderListRepository.getOrderList()
        response?.enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                orderList.postValue(response.body())
                Log.d(TAG, RESPONSE_SUCCESSFUL)
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                t.message?.let { Log.d(TAG, it) }
            }
        })
    }
}