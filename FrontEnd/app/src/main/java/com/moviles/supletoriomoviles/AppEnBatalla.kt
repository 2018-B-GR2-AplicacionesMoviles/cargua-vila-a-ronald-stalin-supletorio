package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable

class AppEnBatalla(var id : Int,
                   var ganador: Int,
                   var idAppPorUsu: Int,
                   var idIntiBatalla: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(ganador)
        parcel.writeInt(idAppPorUsu)
        parcel.writeInt(idIntiBatalla)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppEnBatalla> {
        override fun createFromParcel(parcel: Parcel): AppEnBatalla {
            return AppEnBatalla(parcel)
        }

        override fun newArray(size: Int): Array<AppEnBatalla?> {
            return arrayOfNulls(size)
        }
    }
}