package com.mobilenik.storibank.Data.Network

import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister

interface Repository {

    suspend fun userRegistration(body: UserRegister): Result<UserInformation>
}