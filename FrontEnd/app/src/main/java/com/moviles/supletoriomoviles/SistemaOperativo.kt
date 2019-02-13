package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class SistemaOperativo(val idSO: Int,
                       val nombre: String,
                       val versionApi: Int,
                       val fechaLanzamiento: String,
                       val pesoGigas: Double,
                       val instalado : Boolean): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idSO)
        parcel.writeString(nombre)
        parcel.writeInt(versionApi)
        parcel.writeString(fechaLanzamiento)
        parcel.writeDouble(pesoGigas)
        parcel.writeByte(if (instalado) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SistemaOperativo> {
        override fun createFromParcel(parcel: Parcel): SistemaOperativo {
            return SistemaOperativo(parcel)
        }

        override fun newArray(size: Int): Array<SistemaOperativo?> {
            return arrayOfNulls(size)
        }
    }

}