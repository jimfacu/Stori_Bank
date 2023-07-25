package com.mobilenik.storibank.Views.StartingFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.BaseViewModel
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Domain.GetLoginUserUseCase
import com.mobilenik.storibank.Data.Model.User
import com.mobilenik.storibank.Data.Model.UserLogin
import com.mobilenik.storibank.Data.Result
import com.mobilenik.storibank.Utils.Event
import com.mobilenik.storibank.Utils.StoriBankPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingViewModel @Inject constructor(
    private val getLoginUserUseCase: GetLoginUserUseCase
):BaseViewModel(){

    private val _userInformation = MutableLiveData<Event<String>>()
    val userInformation:LiveData<Event<String>> get() = _userInformation


    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> get() = _error



    fun loginUser(userLogin: UserLogin){
        viewModelScope.launch {
            (Dispatchers.IO)

            val result = getLoginUserUseCase.invoke(userLogin)

            when(result){

                is Result.Success ->{
                    StoriBankPreferences.setUserUid(result.data)
                    _userInformation.postValue(Event(result.data))
                }
                is Result.Error ->{
                    _error.postValue(Event(result.exception!!.message.toString()))
                }
                else ->{
                    _error.postValue(Event(Constants.DESCRIPTION_GENERIC_ERROR))
                }

            }

        }
    }
}