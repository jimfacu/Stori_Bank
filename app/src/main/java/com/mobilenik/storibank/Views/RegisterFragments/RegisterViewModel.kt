package com.mobilenik.storibank.Views.RegisterFragments

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.BaseViewModel
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



@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val saveRegisterUser: SaveRegisterUser,
    private val savePictureUser: SavePictureUser
): BaseViewModel(){

    private val _userInformation = MutableLiveData<Event<UserInformation>>()
    val userInformation: LiveData<Event<UserInformation>> get() = _userInformation

    private val _userPicture = MutableLiveData<Event<String>>()
    val userPicture: LiveData<Event<String>> get() = _userPicture


    init {

    }

    fun registerUser(body:UserRegister){

        viewModelScope.launch {
            (Dispatchers.IO)

            val result = saveRegisterUser.invoke(body)

            if(result is Result.Success){
                StoriBankPreferences.setUserUid(result.data.uuid)
                _userInformation.postValue(Event(result.data))
            }
        }

    }

    fun savePictureUser(picture:Uri){
        viewModelScope.launch {
            (Dispatchers.IO)

            val result = savePictureUser.invoke(picture)

            if(result is Result.Success){
                _userPicture.postValue(Event(result.data))
            }

        }
    }


}
