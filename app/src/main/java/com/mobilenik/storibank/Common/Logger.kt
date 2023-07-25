package com.mobilenik.storibank.Common

import android.util.Log
import com.google.gson.Gson

object LoggerImpl : Logger {
    private const val TAG = Constants.TAG_LOGGER
    private val gson: Gson = Gson()

    /**
     * Registra un mensaje de depuración en el registro con la etiqueta TAG.
     * @param message El mensaje de depuración que se desea registrar.
     */
    override fun debug(message: String) {
        Log.d(TAG, message)
    }

    /**
     * Registra un mensaje de información en el registro con la etiqueta TAG.
     * @param message El mensaje de información que se desea registrar.
     */
    override fun info(message: String) {
        Log.i(TAG, message)
    }

    /**
     * Registra un mensaje de error en el registro con la etiqueta TAG.
     * @param message El mensaje de error que se desea registrar.
     */
    override fun error(message: String) {
        Log.e(TAG, message)
    }

    /**
     * Convierte el objeto proporcionado en formato JSON utilizando Gson y lo
     * registra en el registro de información con la etiqueta TAG.
     * Si el objeto es nulo, no se produce ninguna acción.
     * @param obj El objeto que se desea convertir y registrar en formato JSON.
     */
    override fun obj(obj: Any?) {
        val json = gson.toJson(obj)
        Log.i(TAG, "Object ${obj!!.javaClass} ${json.toString()}")
    }
}

interface Logger {
    /**
     * Registra un mensaje de depuración en el registro.
     * @param message El mensaje de depuración que se desea registrar.
     */
    fun debug(message: String)

    /**
     * Registra un mensaje de información en el registro.
     * @param message El mensaje de información que se desea registrar.
     */
    fun info(message: String)

    /**
     * Registra un mensaje de error en el registro.
     * @param message El mensaje de error que se desea registrar.
     */
    fun error(message: String)

    /**
     * Permite registrar objetos en el registro en formato JSON.
     * Convierte el objeto proporcionado en formato JSON utilizando Gson y lo
     * registra en el registro de información con la etiqueta TAG.
     * Si el objeto es nulo, no se produce ninguna acción.
     * @param obj El objeto que se desea convertir y registrar en formato JSON.
     */
    fun obj(obj: Any?)
}