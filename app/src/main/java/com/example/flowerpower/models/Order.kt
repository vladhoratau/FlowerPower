package com.example.flowerpower.models

import com.google.gson.annotations.SerializedName

class Order {

    @SerializedName("id")
    val orderID: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("price")
    val price: Double? = null

    @SerializedName("deliver_to")
    val deliverTo: String? = null
}