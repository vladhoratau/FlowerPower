package com.example.flowerpower.repositories

import com.example.flowerpower.services.OrderListService

class OrderListRepository(private val orderListService: OrderListService?) {

    fun getOrderList() = orderListService?.getOrderList()

}