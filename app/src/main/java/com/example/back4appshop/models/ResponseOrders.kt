package com.example.back4appshop.models
import com.google.gson.annotations.SerializedName

data class ResponseOrders (
        @SerializedName("objectId")val objectId : String,
        @SerializedName("createdAt")val createdAt : String
)