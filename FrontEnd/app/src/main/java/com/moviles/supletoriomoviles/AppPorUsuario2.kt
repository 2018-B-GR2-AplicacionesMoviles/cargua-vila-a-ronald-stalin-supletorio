package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class AppPorUsuario2(var idAppPorUsu : Int,
                     var nombreApp: String,
                     var nombreUsuario: String,
                     var experienciaApp: Double,
                     var numBatallas: Int,
                     var numRecolectas: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idAppPorUsu)
        parcel.writeString(nombreApp)
        parcel.writeString(nombreUsuario)
        parcel.writeDouble(experienciaApp)
        parcel.writeInt(numBatallas)
        parcel.writeInt(numRecolectas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppPorUsuario2> {
        override fun createFromParcel(parcel: Parcel): AppPorUsuario2 {
            return AppPorUsuario2(parcel)
        }

        override fun newArray(size: Int): Array<AppPorUsuario2?> {
            return arrayOfNulls(size)
        }
    }
}