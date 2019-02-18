package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class AppPorUsuario(var idAppPorUsu : Int,
                    var idApp: Int,
                    var idUsuario: Int,
                    var experienciaApp: Double,
                    var numBatallas: Int,
                    var numRecolectas: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idAppPorUsu)
        parcel.writeInt(idApp)
        parcel.writeInt(idUsuario)
        parcel.writeDouble(experienciaApp)
        parcel.writeInt(numBatallas)
        parcel.writeInt(numRecolectas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppPorUsuario> {
        override fun createFromParcel(parcel: Parcel): AppPorUsuario {
            return AppPorUsuario(parcel)
        }

        override fun newArray(size: Int): Array<AppPorUsuario?> {
            return arrayOfNulls(size)
        }
    }
}