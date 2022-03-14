package com.example.flowerpower.utils

import android.widget.Toast

object ToastMessage {

    fun showMessage(message: String?) {
        Toast.makeText(ApplicationClass.instance, message, Toast.LENGTH_SHORT).show()
    }
}