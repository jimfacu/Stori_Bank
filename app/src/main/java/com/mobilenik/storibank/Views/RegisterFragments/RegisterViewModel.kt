package com.mobilenik.storibank.Views.RegisterFragments

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.BaseViewModel
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Common.Logger
import com.mobilenik.storibank.Common.LoggerImpl
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Result
import com.mobilenik.storibank.Domain.SavePictureUser
import com.mobilenik.storibank.Domain.SaveRegisterUser
import com.mobilenik.storibank.Utils.Event
import com.mobilenik.storibank.Utils.StoriBankPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val saveRegisterUser: SaveRegisterUser,
    private val savePictureUser: SavePictureUser
): BaseViewModel(){

    private val _userInformation = MutableLiveData<Event<UserInformation>>()
    val userInformation: LiveData<Event<UserInformation>> get() = _userInformation

    private val _userPicture = MutableLiveData<Event<String>>()
    val userPicture: LiveData<Event<String>> get() = _userPicture

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> get() = _error

    private val logger : Logger = LoggerImpl

    fun registerUser(body:UserRegister){

        viewModelScope.launch {
            (Dispatchers.IO)

            logger.obj(body)
            val result = saveRegisterUser.invoke(body)


            when(result){

                is Result.Success ->{
                    logger.obj(result.data)
                    StoriBankPreferences.setUserUid(result.data.uuid)
                    _userInformation.postValue(Event(result.data))
                    }
                is Result.Error ->{
                    logger.error(result.exception!!.message!!)
                    _error.postValue(Event(result.exception.message.toString()))
                }
                else ->{
                    logger.error(Constants.DESCRIPTION_GENERIC_ERROR)
                    _error.postValue(Event(Constants.DESCRIPTION_GENERIC_ERROR))
                }

            }
        }

    }

    fun savePictureUser(picture:Uri){
        viewModelScope.launch {
            (Dispatchers.IO)
            logger.obj(picture)
            val result = savePictureUser.invoke(picture)

            when(result){

                is Result.Success ->{
                    logger.info(result.data)
                    _userPicture.postValue(Event(result.data))
                }
                is Result.Error ->{
                    logger.error(result.exception!!.message.toString())
                    _error.postValue(Event(result.exception.message.toString()))
                }
                else ->{
                    logger.error(Constants.DESCRIPTION_GENERIC_ERROR)
                    _error.postValue(Event(Constants.DESCRIPTION_GENERIC_ERROR))
                }

            }

        }
    }
}
