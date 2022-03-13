package com.example.flowerpower.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowerpower.R
import com.example.flowerpower.utils.Navigator
import com.example.flowerpower.views.fragments.OrderListFragment

class MainActivity : AppCompatActivity() {

    private val navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.replaceFragment((OrderListFragment.newInstance()), this)
    }
}