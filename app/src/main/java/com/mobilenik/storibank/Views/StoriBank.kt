package com.mobilenik.storibank.Views

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StoriBank : Application(){

    companion object {
        lateinit var instance : StoriBank
            private set //--no puede asignarse un valor desde afuera de la clase
    }


    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate()
        instance = this
    }
}