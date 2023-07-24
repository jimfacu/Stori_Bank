package com.mobilenik.storibank.Data.Network

import android.net.Uri
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Result

interface Repository {

    suspend fun userRegistration(body: UserRegister): Result<UserInformation>

    suspend fun saveUserPicture(pictureUser:Uri): Result<String>
}