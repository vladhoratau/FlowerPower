package com.example.flowerpower.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.flowerpower.R

class Navigator {

    fun replaceFragment(fragment: Fragment, fragmentActivity: FragmentActivity?) {
        val fragmentTransaction = fragmentActivity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction?.commit()
    }
}