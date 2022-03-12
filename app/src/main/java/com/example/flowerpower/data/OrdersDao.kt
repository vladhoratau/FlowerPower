package com.example.flowerpower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flowerpower.models.Order

@Dao
interface OrdersDao {
    @Query("UPDATE orders_table SET description = :description, price = :price, deliver_to = :deliverTo WHERE orderID = :orderID")
    suspend fun update(orderID: String, description: String?, price: Double?, deliverTo: String?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: Order): Long

    @Query("UPDATE orders_table SET status = :status WHERE orderID = :orderID")
    suspend fun updateStatus(orderID: String, status: String)

    @Query("Select * from orders_table order by orderID ASC")
    fun getAllOrders(): LiveData<List<Order>>

    @Query("Select * from orders_table where orderID = :orderID")
    fun getOrderByID(orderID: String): LiveData<Order>
}