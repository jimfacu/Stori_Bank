package com.mobilenik.storibank.Domain

import com.mobilenik.storibank.Data.Model.User
import com.mobilenik.storibank.Data.Network.Repository
import com.mobilenik.storibank.Data.Result
import javax.inject.Inject

class GetUserInformation @Inject constructor(private val defaultRepository: Repository) {

    suspend operator fun invoke(uid:String): Result<User> = defaultRepository.getUserInformation(uid)

}