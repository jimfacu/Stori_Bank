package com.mobilenik.storibank.Utils

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

class DialogManager {

    interface IListener {
        fun onClick()
    }

    constructor(
        activity: FragmentActivity?,
        msg: String?,
        listener: IListener?
    ) {
        val builder = AlertDialog.Builder(
            activity!!
        )
        builder.setMessage(msg)
        builder.setPositiveButton(
            "OK"
        ) { _: DialogInterface?, _: Int ->

            try {
                listener?.onClick()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()
    }
}