package com.mobilenik.storibank.Data

import android.net.Uri
import com.mobilenik.storibank.Data.Model.User
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserLogin
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Network.FirebaseDataSource.FirebaseRemoteDataSource
import com.mobilenik.storibank.Data.Network.Repository


import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val firebaseRemoteDataSource: FirebaseRemoteDataSource
) : Repository
{
    override suspend fun userRegistration(body: UserRegister): Result<UserInformation> = firebaseRemoteDataSource.userRegistration(body)
    override suspend fun saveUserPicture(pictureUser: Uri): Result<String> = firebaseRemoteDataSource.saveUserPicture(pictureUser)
    override suspend fun loginUser(userLogin: UserLogin): Result<String> = firebaseRemoteDataSource.loginUser(userLogin)
    override suspend fun getUserInformation(uid: String): Result<User> = firebaseRemoteDataSource.getUserInformation(uid)


}