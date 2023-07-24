package com.mobilenik.storibank.Views.StartingFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.BaseViewModel
import com.mobilenik.storibank.Domain.GetLoginUserUseCase
import com.mobilenik.storibank.Data.Model.User
import com.mobilenik.storibank.Utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingViewModel @Inject constructor(
    private val getLoginUserUseCase: GetLoginUserUseCase
):BaseViewModel(){

    private val _userInformation = MutableLiveData<Event<User>>()
    val userInformation:LiveData<Event<User>> get() = _userInformation


   /* private val _headerPromotions = MutableLiveData<MutableList<Promotions>>()
    val headerPromotions: LiveData<MutableList<Promotions>> get() = _headerPromotions*/



    fun loginUser(){
        viewModelScope.launch {
            (Dispatchers.IO)


        }
    }
}