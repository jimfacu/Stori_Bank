package com.mobilenik.storibank.Utils

import com.mobilenik.storibank.BuildConfig
import com.mobilenik.storibank.Views.StoriBank

object StoriBankPreferences: Preferences(StoriBank.instance, BuildConfig.APPLICATION_ID) {

    private const val KEY_USER_UID = "KEY_USER_UID"

    fun setUserUid(uid:String?){
        set(KEY_USER_UID,uid)
    }

    fun getAUserUid():String?{
        return get(KEY_USER_UID)
    }
}