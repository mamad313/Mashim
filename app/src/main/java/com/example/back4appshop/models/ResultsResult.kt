package com.example.back4appshop.models

import com.google.gson.annotations.SerializedName

data class ResultsResult (
    @SerializedName("results") val results : List<ResultsProfileData>
    )