package com.mobilenik.storibank.Data.Model

data class User(
    val name:String?, val lastName:String?, val email:String?
    , val balance:String?, val moves: ArrayList<HashMap<String, String>>
){
}