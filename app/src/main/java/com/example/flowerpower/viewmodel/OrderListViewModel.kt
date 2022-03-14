package com.example.flowerpower.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowerpower.R
import com.example.flowerpower.models.Order
import com.example.flowerpower.repositories.OrdersRepository
import com.example.flowerpower.services.OrdersService
import com.example.flowerpower.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListViewModel : ViewModel() {

    companion object {
        private val TAG: String? = OrderListViewModel::class.java.canonicalName
    }

    private val ordersRepository : OrdersRepository = OrdersRepository(OrdersService.getInstance())
    val orders = MutableLiveData<List<Order>>()

    fun getOrderList() {
        val response = ordersRepository.getOrders()
        response?.enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                orders.postValue(response.body())
                Log.d(TAG, ApplicationClass.instance.getString(R.string.GET_REQUEST_SUCCESSFUL))
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                t.message?.let { Log.d(TAG, it) }
            }
        })
    }
}