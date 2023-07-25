package com.mobilenik.storibank.Domain

import com.mobilenik.storibank.Data.Model.UserLogin
import com.mobilenik.storibank.Data.Network.Repository
import com.mobilenik.storibank.Data.Result
import javax.inject.Inject

class GetLoginUserUseCase  @Inject constructor(private val defaultRepository: Repository) {

    suspend operator fun invoke(userLogin: UserLogin): Result<String> = defaultRepository.loginUser(userLogin)

}