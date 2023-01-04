package com.example.back4appshop.models
import com.google.gson.annotations.SerializedName


data class JasonCategory (
		@SerializedName("results") val results : List<CategoryResults>
)