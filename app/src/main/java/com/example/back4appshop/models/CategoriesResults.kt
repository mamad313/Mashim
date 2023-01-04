package com.example.back4appshop.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class CategoriesResults (
	@SerializedName("objectId") val objectId : String,
	@SerializedName("title") val title : String,
	@SerializedName("createdAt") val createdAt : String,
	@SerializedName("updatedAt") val updatedAt : String,
	@SerializedName("icon") val icon : String
): Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!) {
	}

	override fun describeContents(): Int {
		TODO("Not yet implemented")
	}

	override fun writeToParcel(dest: Parcel?, flags: Int) {
		TODO("Not yet implemented")
	}

	companion object CREATOR : Parcelable.Creator<CategoriesResults> {
		override fun createFromParcel(parcel: Parcel): CategoriesResults {
			return CategoriesResults(parcel)
		}

		override fun newArray(size: Int): Array<CategoriesResults?> {
			return arrayOfNulls(size)
		}
	}
}