package com.example.flowerpower.factory

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowerpower.R
import com.example.flowerpower.repositories.OrderListRepository
import com.example.flowerpower.viewmodel.OrderListViewModel

class ViewModelFactory constructor(private val orderListRepository: OrderListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            OrderListViewModel(this.orderListRepository) as T
        } else {
            throw IllegalAccessException(
                Resources.getSystem().getString(R.string.MODEL_CLASS_ERROR)
            )
        }
    }

}