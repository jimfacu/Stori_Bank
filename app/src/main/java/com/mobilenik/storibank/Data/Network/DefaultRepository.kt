package com.mobilenik.storibank.Data.Network

import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val firebaseRemoteDataSource: FirebaseRemoteDataSource) : Repository
{
    override suspend fun userRegistration(body: UserRegister): Result<UserInformation> = firebaseRemoteDataSource.userRegistration(body)


}