package com.example.flowerpower.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flowerpower.models.Order

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order)

    @Update
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)

    @Query("Select * from orders_table order by orderID ASC")
    fun getAllOrders(): LiveData<List<Order>>
}