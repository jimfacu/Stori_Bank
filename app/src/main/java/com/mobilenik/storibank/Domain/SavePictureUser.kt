package com.mobilenik.storibank.Domain

import android.net.Uri
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Network.Repository
import com.mobilenik.storibank.Data.Result
import javax.inject.Inject

class SavePictureUser @Inject constructor(private val defaultRepository: Repository) {

    suspend operator fun invoke(body: Uri): Result<String> = defaultRepository.saveUserPicture(body)
}