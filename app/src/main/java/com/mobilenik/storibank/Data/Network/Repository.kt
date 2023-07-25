package com.mobilenik.storibank.Data.Network

import android.net.Uri
import com.mobilenik.storibank.Data.Model.User
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserLogin
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Result

interface Repository {

    suspend fun userRegistration(body: UserRegister): Result<UserInformation>

    suspend fun saveUserPicture(pictureUser:Uri): Result<String>

    suspend fun loginUser(userLogin: UserLogin): Result<String>

    suspend fun getUserInformation(uid:String) :Result<User>
}