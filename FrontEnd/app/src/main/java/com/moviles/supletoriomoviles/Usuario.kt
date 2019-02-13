package com.moviles.supletoriomoviles

import android.os.Parcel
import android.os.Parcelable


class Usuario(val idUser:Int,
              val nombre:String,
              val apellido:String,
              val fechaNacimiento: String,
              val email: String,
              val password:String,
              val total_oro:Int,
              val total_experiencia:Int):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idUser)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeInt(total_oro)
        parcel.writeInt(total_experiencia)

    }
    override fun describeContents(): Int {
       return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}