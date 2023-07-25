package com.mobilenik.storibank.Views.HomeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilenik.storibank.BaseViewModel
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Data.Model.Moves
import com.mobilenik.storibank.Data.Model.User
import com.mobilenik.storibank.Data.Result
import com.mobilenik.storibank.Domain.GetUserInformation
import com.mobilenik.storibank.Utils.Event
import com.mobilenik.storibank.Utils.StoriBankPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserInformation: GetUserInformation
):BaseViewModel() {

    private val _userInformation = MutableLiveData<Event<User>>()
    val userInformation: LiveData<Event<User>> get() = _userInformation

    private val _moveList = MutableLiveData<Event<List<Moves>>>()
    val moveList: LiveData<Event<List<Moves>>> get() = _moveList

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> get() = _error

    init {
        getUserInformation(StoriBankPreferences.getAUserUid()!!)
    }


    fun getUserInformation(uid:String){
        viewModelScope.launch {
            (Dispatchers.IO)

            val result = fetchUserInformation.invoke(uid)

            when(result){

                is Result.Success ->{
                    _moveList.postValue(Event(fetchList(result.data.moves)))
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


    private fun fetchList(moves: ArrayList<HashMap<String,String>>?):ArrayList<Moves> {
        val newArray = ArrayList<Moves>()
        for(move in moves!!){
            newArray.add(Moves(move.get(Constants.HASHMAP_STATUS_VALUE)!!,
                move.get(Constants.HASHMAP_AMOUNT_VALUE)!!,move.get(Constants.HASHMAP_NAME_VALUE)!!))
        }
        return newArray
    }
}