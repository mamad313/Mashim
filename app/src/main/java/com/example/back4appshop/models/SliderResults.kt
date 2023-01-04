package com.example.back4appshop.models
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



data class SliderResults (

		@SerializedName("objectId") val objectId : String,
		@SerializedName("data") val data : String,
		@SerializedName("createdAt") val createdAt : String,
		@SerializedName("updatedAt") val updatedAt : String,
		@SerializedName("title") val title : String
): Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!,
			parcel.readString()!!) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(objectId)
		parcel.writeString(data)
		parcel.writeString(createdAt)
		parcel.writeString(updatedAt)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<SliderResults> {
		override fun createFromParcel(parcel: Parcel): SliderResults {
			return SliderResults(parcel)
		}

		override fun newArray(size: Int): Array<SliderResults?> {
			return arrayOfNulls(size)
		}
	}
}