package com.example.back4appshop.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class CategoryResults(
		@SerializedName("objectId") var objectId : String,
		@SerializedName("title") val title : String,
		@SerializedName("createdAt") val createdAt : String,
		@SerializedName("updatedAt") val updatedAt : String,
		@SerializedName("image") val image : String,
		@SerializedName("ID") val ID : String
):Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(objectId)
		parcel.writeString(title)
		parcel.writeString(createdAt)
		parcel.writeString(updatedAt)
		parcel.writeString(image)
		parcel.writeString(ID)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<CategoryResults> {
		override fun createFromParcel(parcel: Parcel): CategoryResults {
			return CategoryResults(parcel)
		}

		override fun newArray(size: Int): Array<CategoryResults?> {
			return arrayOfNulls(size)
		}
	}
}

