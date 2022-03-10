package com.example.flowerpower.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flowerpower.models.Order

@Database(entities = [Order::class], version = 1, exportSchema = false)

abstract class OrderDatabase : RoomDatabase() {

    abstract fun getOrdersDao(): OrdersDao

    companion object {

        @Volatile
        private var INSTANCE: OrderDatabase? = null

        fun getDatabase(context: Context): OrderDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDatabase::class.java,
                    "order_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}