package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class Clima( var temp: Double,
             var pressure: Double,
             var humidity : Double,
             var temp_min : Double,
             var temp_max : Double): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temp)
        parcel.writeDouble(pressure)
        parcel.writeDouble(humidity)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(temp_max)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Clima> {
        override fun createFromParcel(parcel: Parcel): Clima {
            return Clima(parcel)
        }

        override fun newArray(size: Int): Array<Clima?> {
            return arrayOfNulls(size)
        }
    }
}