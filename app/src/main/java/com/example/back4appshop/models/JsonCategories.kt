package com.example.back4appshop.models

import com.google.gson.annotations.SerializedName

data class JsonCategories (
	    @SerializedName("results") val results : List<CategoriesResults>
)