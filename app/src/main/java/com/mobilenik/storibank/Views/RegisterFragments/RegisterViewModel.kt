package com.mobilenik.storibank.Views.RegisterFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.BaseViewModel
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.Data.Network.Result
import com.mobilenik.storibank.Data.Network.User
import com.mobilenik.storibank.Domain.GetLoginUserUseCase
import com.mobilenik.storibank.Domain.SaveRegisterUser
import com.mobilenik.storibank.Utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val saveRegisterUser: SaveRegisterUser
): BaseViewModel(){

    private val _userInformation = MutableLiveData<Event<UserInformation>>()
    val userInformation: LiveData<Event<UserInformation>> get() = _userInformation


    init {

    }

    fun registerUser(body:UserRegister){

        viewModelScope.launch {
            (Dispatchers.IO)

            val result = saveRegisterUser.invoke(body)

            if(result is Result.Success){
                _userInformation.postValue(Event(result.data))
            }
        }

    }


}
