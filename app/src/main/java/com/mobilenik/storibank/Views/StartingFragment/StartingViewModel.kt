package com.mobilenik.storibank.Views.StartingFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Common.Logger
import com.mobilenik.storibank.Common.LoggerImpl
import com.mobilenik.storibank.Domain.GetLoginUserUseCase
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
):ViewModel(){

    private val _userInformation = MutableLiveData<Event<String>>()
    val userInformation:LiveData<Event<String>> get() = _userInformation


    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> get() = _error

    private val logger : Logger = LoggerImpl



    fun loginUser(userLogin: UserLogin){
        viewModelScope.launch {
            (Dispatchers.IO)
            logger.obj(userLogin)
            val result = getLoginUserUseCase.invoke(userLogin)

            when(result){

                is Result.Success ->{
                    logger.info(result.data)
                    StoriBankPreferences.setUserUid(result.data)
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
}