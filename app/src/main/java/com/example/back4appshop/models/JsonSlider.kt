package com.example.back4appshop.models
import com.google.gson.annotations.SerializedName


data class JsonSlider (

	@SerializedName("results") val results : List<SliderResults>
)