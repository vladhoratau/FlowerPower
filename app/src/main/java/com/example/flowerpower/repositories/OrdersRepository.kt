package com.example.flowerpower.repositories

import com.example.flowerpower.services.OrdersService

class OrdersRepository(private val ordersService: OrdersService?) {

    fun getOrders() = ordersService?.getOrders()
}