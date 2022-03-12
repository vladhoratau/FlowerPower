package com.example.flowerpower.services

import com.example.flowerpower.R
import com.example.flowerpower.models.Order
import com.example.flowerpower.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface OrderListService {

    @GET("orders")
    fun getOrderList(): Call<List<Order>>

    companion object {
        val BASE_URL = ApplicationClass.instance.getString(R.string.BASE_URL)
        var orderListService: OrderListService? = null

        fun getInstance(): OrderListService? {
            if (orderListService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                orderListService = retrofit.create(OrderListService::class.java)
            }
            return orderListService
        }
    }
}