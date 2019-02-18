package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class InteraccionTipoRecoleccion(var idIntiR: Int,
                                 var idAppPorUsuario: Int,
                                 var idApp: Int,
                                 var oroRecolectado: Int,
                                 var experienciaRecolectado: Int): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idIntiR)
        parcel.writeInt(idAppPorUsuario)
        parcel.writeInt(idApp)
        parcel.writeInt(oroRecolectado)
        parcel.writeInt(experienciaRecolectado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InteraccionTipoRecoleccion> {
        override fun createFromParcel(parcel: Parcel): InteraccionTipoRecoleccion {
            return InteraccionTipoRecoleccion(parcel)
        }

        override fun newArray(size: Int): Array<InteraccionTipoRecoleccion?> {
            return arrayOfNulls(size)
        }
    }
}
