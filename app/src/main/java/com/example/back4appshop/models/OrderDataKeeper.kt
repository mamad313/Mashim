package com.example.back4appshop.models

import com.google.gson.annotations.SerializedName

data class OrderDataKeeper  (

        @SerializedName("userId") val userId : String,
        @SerializedName("productId") val productId : String,
    @SerializedName("count") val count : String


)