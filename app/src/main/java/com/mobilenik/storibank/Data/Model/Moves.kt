package com.mobilenik.storibank.Data.Model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Moves (val Estado:String, val Monto:String,val Nombre:String) : Parcelable {

    // Describe el contenido del objeto Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Escribe los datos del objeto en un parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Estado)
        parcel.writeString(Monto)
        parcel.writeString(Nombre)
    }

    // Este companion object se utiliza para crear el objeto Parcelable a partir de un Parcel
    companion object CREATOR : Parcelable.Creator<Moves> {
        // Lee los datos del parcel y crea un objeto MiObjeto
        override fun createFromParcel(parcel: Parcel): Moves {
            return Moves(parcel.readString() ?: "",parcel.readString() ?: "",parcel.readString() ?: "")
        }

        // Crea un array de objetos MiObjeto del tamaño especificado
        override fun newArray(size: Int): Array<Moves?> {
            return arrayOfNulls(size)
        }
    }
}