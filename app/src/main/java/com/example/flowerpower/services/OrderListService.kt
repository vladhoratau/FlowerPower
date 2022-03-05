package com.example.flowerpower.services

import android.content.res.Resources
import com.example.flowerpower.R
import com.example.flowerpower.models.Order
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface OrderListService {

    @GET("endpoint_orders")
    fun getOrderList(): Call<List<Order>>

    companion object {
        var orderListService: OrderListService? = null
        fun getInstance(): OrderListService? {
            if (orderListService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://demo6394362.mockable.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                orderListService = retrofit.create(OrderListService::class.java)
            }
            return orderListService
        }
    }
}