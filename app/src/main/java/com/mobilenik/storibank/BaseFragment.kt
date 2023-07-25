package com.mobilenik.storibank

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mobilenik.storibank.Utils.DialogManager
import com.mobilenik.storibank.Views.MainActivity

abstract class BaseFragment : Fragment() {

    var dialog: AlertDialog? = null


    fun showMessage(message: String?) {
        showMessage(message, null)
    }

    fun showMessage(message: String?, listener: DialogManager.IListener?) {
        try {
            if (activity?.isFinishing == false) {
                DialogManager(
                    activity,
                    message,
                    listener
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    protected fun showProgress(){
        (activity as MainActivity).showProgress()
    }

    protected fun hideProgress(){
        (activity as MainActivity).hideProgress()
    }
}

