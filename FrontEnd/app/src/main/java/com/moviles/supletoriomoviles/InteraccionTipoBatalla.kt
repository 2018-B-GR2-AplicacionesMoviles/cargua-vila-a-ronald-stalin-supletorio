package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class InteraccionTipoBatalla(var idInti: Int,
                             var idApp : Int,
                             var clima : Int,
                             var turnosJugados : Int,
                             var recompensaOro : Int,
                             var recompensaExperiencia : Int,
                             var estado : String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idInti)
        parcel.writeInt(idApp)
        parcel.writeInt(clima)
        parcel.writeInt(turnosJugados)
        parcel.writeInt(recompensaOro)
        parcel.writeInt(recompensaExperiencia)
        parcel.writeString(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InteraccionTipoBatalla> {
        override fun createFromParcel(parcel: Parcel): InteraccionTipoBatalla {
            return InteraccionTipoBatalla(parcel)
        }

        override fun newArray(size: Int): Array<InteraccionTipoBatalla?> {
            return arrayOfNulls(size)
        }
    }
}