package ru.taximaster.testapp.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Location(@SerializedName("latitude") @Expose val latitude: Double = .0,
               @SerializedName("longitude") @Expose val longitude: Double = .0,
               @SerializedName("accuracy") @Expose val accuracy: Int = 1,
               @SerializedName("context") @Expose val context: Int = 0) : Parcelable {

	constructor() : this(.0, .0, 1, 0)

	constructor(source: Parcel) : this(
			source.readDouble(),
			source.readDouble(),
			source.readInt(),
			source.readInt()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeDouble(latitude)
		writeDouble(longitude)
		writeInt(accuracy)
		writeInt(context)
	}

	override fun equals(other: Any?): Boolean {
		if (other is Location) {
			return latitude == other.latitude
					&& longitude == other.longitude
					&& accuracy == other.accuracy
					&& context == other.context
		}
		return false
	}


	override fun toString(): String {
		return "Location(latitude=$latitude, longitude=$longitude, accuracy=$accuracy, context=$context)"
	}

	override fun hashCode(): Int {
		var result = latitude.hashCode()
		result = 31 * result + longitude.hashCode()
		result = 31 * result + accuracy
		result = 31 * result + context
		return result
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Location> = object : Parcelable.Creator<Location> {
			override fun createFromParcel(source: Parcel): Location = Location(source)
			override fun newArray(size: Int): Array<Location?> = arrayOfNulls(size)
		}
	}
}