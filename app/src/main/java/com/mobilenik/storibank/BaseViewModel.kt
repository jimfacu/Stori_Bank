package com.mobilenik.storibank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobilenik.storibank.Utils.Event

abstract class BaseViewModel : ViewModel() {

    protected val message //--Generally is used for showing a dialog
            : MutableLiveData<String> = MutableLiveData()
    protected val genericMessage //--Generally is used for showing a toast
            : MutableLiveData<String> = MutableLiveData()
    protected val genericError: MutableLiveData<Exception> = MutableLiveData()
    protected val progress: MutableLiveData<Boolean> = MutableLiveData()
    protected val nextActivity: MutableLiveData<Event<Boolean>> = MutableLiveData() //--se usa cuando hay un único destino o como destino default

    fun getMessage(): LiveData<String?> {
        return message
    }

    fun getGenericMessage(): LiveData<String?> {
        return genericMessage
    }

    fun getGenericError(): LiveData<java.lang.Exception?> {
        return genericError
    }

    fun getProgress(): LiveData<Boolean> {
        return progress
    }

    fun getNextActivity(): LiveData<Event<Boolean>> {
        return nextActivity
    }
}