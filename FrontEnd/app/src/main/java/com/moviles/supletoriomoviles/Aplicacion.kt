package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class Aplicacion(var idAP : Int,
                 var pesoGigas : Double,
                 var versiones : Int,
                 var nombres : String,
                 var urlDescarga: String,
                 var fechaLanzamiento : String,
                 var costo : Double,
                 var soId : Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idAP)
        parcel.writeDouble(pesoGigas)
        parcel.writeInt(versiones)
        parcel.writeString(nombres)
        parcel.writeString(urlDescarga)
        parcel.writeString(fechaLanzamiento)
        parcel.writeDouble(costo)
        parcel.writeInt(soId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Aplicacion> {
        override fun createFromParcel(parcel: Parcel): Aplicacion {
            return Aplicacion(parcel)
        }

        override fun newArray(size: Int): Array<Aplicacion?> {
            return arrayOfNulls(size)
        }
    }
}