package com.mobilenik.storibank.Domain

import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Network.Repository
import com.mobilenik.storibank.Data.Network.Result
import javax.inject.Inject

class SaveRegisterUser @Inject constructor(private val defaultRepository: Repository) {

    suspend operator fun invoke(body: UserRegister): Result<UserInformation> = defaultRepository.userRegistration(body)
}