package com.example.flowerpower.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowerpower.models.Order
import com.example.flowerpower.repositories.OrderListRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListViewModel constructor(private val orderListRepository: OrderListRepository) :
    ViewModel() {
    companion object {
        private val TAG: String = OrderListViewModel::class.java.canonicalName
    }

    val orderList = MutableLiveData<List<Order>>()

    fun getOrderList() {
        val response = orderListRepository.getOrderList()
        response?.enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                orderList.postValue(response.body())
                Log.d(TAG, "response succesfull")
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                t.message?.let { Log.d(TAG, it) }
            }
        })
    }
}