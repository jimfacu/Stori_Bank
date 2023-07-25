package com.mobilenik.storibank.Data.Model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Moves (val Estado:String, val Monto:String,val Nombre:String):Parcelable {

}