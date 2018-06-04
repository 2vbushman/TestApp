package ru.taximaster.testapp.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
		@SerializedName("id") @Expose val id: String,
		@SerializedName("owner") @Expose val owner: String,
		@SerializedName("secret") @Expose val secret: String,
		@SerializedName("server") @Expose val server: String,


		@SerializedName("farm") @Expose val farm: String,
		@SerializedName("title") @Expose val title: String,
		@SerializedName("ispublic") @Expose val ispublic: String,
		@SerializedName("isfriend") @Expose val isfriend: String,
		@SerializedName("isfamily") @Expose val isfamily: String,
		var location: Location? = null

) : Parcelable {
	override fun equals(other: Any?): Boolean {
		if (other is Photo) {
			return id == other.id
					&& owner == other.owner
					&& secret == other.secret
					&& server == other.server
					&& farm == other.farm
					&& title == other.title
					&& ispublic == other.ispublic
					&& isfriend == other.isfriend
					&& isfamily == other.isfamily

		}
		return false
	}

	override fun hashCode(): Int {
		var result = id.hashCode()
		result = 31 * result + owner.hashCode()
		result = 31 * result + secret.hashCode()
		result = 31 * result + server.hashCode()
		result = 31 * result + farm.hashCode()
		result = 31 * result + title.hashCode()
		result = 31 * result + ispublic.hashCode()
		result = 31 * result + isfriend.hashCode()
		result = 31 * result + isfamily.hashCode()
		return result
	}

	fun getImageUrl(): String {
		return "http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg"
	}

	override fun toString(): String {
		return "Photo(id='$id', " +
				"owner='$owner', " +
				"secret='$secret', " +
				"server='$server', " +
				"farm='$farm', " +
				"title='$title', " +
				"ispublic='$ispublic', " +
				"isfriend='$isfriend', " +
				"isfamily='$isfamily')"
	}

	constructor(source: Parcel) : this(
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readParcelable<Location>(Location::class.java.classLoader)
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeString(id)
		writeString(owner)
		writeString(secret)
		writeString(server)
		writeString(farm)
		writeString(title)
		writeString(ispublic)
		writeString(isfriend)
		writeString(isfamily)
		writeParcelable(location, 0)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
			override fun createFromParcel(source: Parcel): Photo = Photo(source)
			override fun newArray(size: Int): Array<Photo?> = arrayOfNulls(size)
		}
	}
}