package com.example.marlon.guidetour

import android.os.Parcel
import android.os.Parcelable

data class Place(var name: String="The name",
                 var description: String="Description...",
                 var image: Int=(R.drawable.cerro_verde),
                 var phone: String="22222222",
                 var category: String="Park",
                 var webPage: String="https://developer.android.com",
                 var isExpanded:Boolean=false) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(image)
        parcel.writeString(phone)
        parcel.writeString(category)
        parcel.writeString(webPage)
        parcel.writeByte(if (isExpanded) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }
    }

}