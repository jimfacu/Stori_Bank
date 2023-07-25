package com.mobilenik.storibank.Common

import android.util.Log
import com.google.gson.Gson

object LoggerImpl : Logger{
    private const val TAG = "Logger"
    private val gson: Gson = Gson()

    override fun debug(message: String) {
        Log.d(TAG, message)
    }

    override fun info(message: String) {
        Log.i(TAG, message)
    }

    override fun error(message: String) {
        Log.e(TAG, message)
    }

    override fun obj(obj: Any?) {
        val json = gson.toJson(obj)
        Log.i(TAG,"Object ${obj!!.javaClass} ${json.toString()}")
    }


}

interface Logger {
    fun debug(message: String)
    fun info(message: String)
    fun error(message: String)
    fun obj(obj:Any?)
}