package com.example.flowerpower.factory

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowerpower.R
import com.example.flowerpower.utils.ApplicationClass
import com.example.flowerpower.viewmodel.DBOrdersViewModel
import com.example.flowerpower.viewmodel.OrderListViewModel

class ViewModelFactory :

    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(OrderListViewModel::class.java) -> {
                OrderListViewModel() as T
            }
            modelClass.isAssignableFrom(DBOrdersViewModel::class.java) -> {
                DBOrdersViewModel(ApplicationClass.instance) as T
            }
            else -> {
                throw IllegalAccessException(
                    Resources.getSystem().getString(R.string.MODEL_CLASS_ERROR)
                )
            }
        }
    }
}