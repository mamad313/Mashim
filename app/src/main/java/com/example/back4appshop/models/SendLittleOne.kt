package com.example.back4appshop.models

import com.google.gson.annotations.SerializedName

data class SendLittleOne (
    @SerializedName("results") val results : List<OrderDataKeeper>
)