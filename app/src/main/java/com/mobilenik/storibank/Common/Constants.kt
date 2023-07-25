package com.mobilenik.storibank.Common

import android.os.Build

class Constants {

    companion object {
        const val REFERENCE_DATABASE_USERS = "Users"
        const val MY_PERMISSIONS_REQUEST_CAMERA = 99

        const val FIREBASE_FIELD_NAME = "Nombre"
        const val FIREBASE_FIELD_LAST_NAME = "Apellido"
        const val FIREBASE_FIELD_EMAIL = "Email"
        const val FIREBASE_FIELD_BALANCE = "Balance"
        const val FIREBASE_FIELD_MOVES = "Movimientos"

        const val FIREBASE_PATH_PICTURE= "PictureUsers/"

        const val FIREBASE_FIELD_BALANCE_VALUE = "100000"

        const val DESCRIPTION_GENERIC_ERROR = " UPS ! Hubo un problema en el servidor , intente nuevamente dentro de unos minutos"

        const val HASHMAP_NAME_VALUE = "Nombre"
        const val HASHMAP_STATUS_VALUE = "Estado"
        const val HASHMAP_AMOUNT_VALUE= "Monto"

    }
}