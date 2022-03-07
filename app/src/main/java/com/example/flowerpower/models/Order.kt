package com.example.flowerpower.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Order : Serializable {

    @SerializedName("id")
    val orderID: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("price")
    val price: Double? = null

    @SerializedName("deliver_to")
    val deliverTo: String? = null

    @SerializedName("status")
    val status: String? = null
}