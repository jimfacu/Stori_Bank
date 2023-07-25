package com.mobilenik.storibank.Data.Network.FirebaseDataSource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Data.Model.*
import kotlin.collections.HashMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.mobilenik.storibank.Data.Result
import com.mobilenik.storibank.Utils.StoriBankPreferences

class FirebaseRemoteDataSource : FirebaseDataSource {

    private val loader = FirebaseAuth.getInstance()
    private val documentReference = FirebaseFirestore.getInstance()



    override suspend fun userRegistration(body:UserRegister): Result<UserInformation> =

        suspendCoroutine { cont ->

            try {
                loader.createUserWithEmailAndPassword(body.email,body.password).addOnCompleteListener {
                    if(it.isSuccessful){
                        var hp = HashMap<String, String>()
                        hp.put(Constants.FIREBASE_FIELD_NAME,body.name)
                        hp.put(Constants.FIREBASE_FIELD_LAST_NAME,body.lastName)
                        hp.put(Constants.FIREBASE_FIELD_EMAIL,body.email)
                        hp.put(Constants.FIREBASE_FIELD_BALANCE,Constants.FIREBASE_FIELD_BALANCE_VALUE)
                        documentReference.collection(Constants.REFERENCE_DATABASE_USERS).
                                document(loader.uid!!).set(hp).
                        addOnSuccessListener {
                            cont.resume(
                                Result.Success(
                                    UserInformation(
                                        body.name,
                                        body.lastName,
                                        body.email,
                                        loader.uid,
                                        true
                                    )
                                )
                            )
                        }.addOnFailureListener{ exception ->
                            cont.resume(Result.Error(exception))
                        }
                    }else{
                        cont.resume(Result.Error(it.exception))
                    }
                }
            }catch (e:Exception){
                cont.resume(Result.Error(e))
            }
        }

    override suspend fun saveUserPicture(pictureUser: Uri): Result<String> =
        suspendCoroutine { cont ->
            try {
                val storage = FirebaseStorage.getInstance()
                    .getReference(Constants.FIREBASE_PATH_PICTURE+
                            StoriBankPreferences.getAUserUid())
                storage.putFile(pictureUser).addOnSuccessListener {
                    cont.resume(Result.Success(Constants.MESSAGE_PICTURE_UPLOAD))
                }
                    .addOnFailureListener{
                        cont.resume(Result.Error(it))
                    }

            }catch (e:Exception){
                cont.resume(Result.Error(e))
        }
    }

    override suspend fun loginUser(userLogin: UserLogin): Result<String> =
        suspendCoroutine { cont ->
            try {
                loader.signInWithEmailAndPassword(userLogin.email,userLogin.password).addOnSuccessListener { it ->
                    if (it.user != null){
                        cont.resume(Result.Success(it.user!!.uid))
                    }else{
                        cont.resume(Result.Error(Exception(Constants.MESSAGE_ERROR_LOGIN)))
                    }
                }
                    .addOnFailureListener{ it ->
                        cont.resume(Result.Error(it))
                    }
            }catch (e:Exception){
                cont.resume(Result.Error(e))
            }
        }

    override suspend fun getUserInformation(uid: String): Result<User> =
        suspendCoroutine { cont ->
            try {
                val informationUser = documentReference.collection(Constants.REFERENCE_DATABASE_USERS).document(uid)
                informationUser.addSnapshotListener(object : EventListener<DocumentSnapshot>{
                    override fun onEvent(
                        value: DocumentSnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                       val user = User(
                           value!!.getString(Constants.FIREBASE_FIELD_NAME),
                           value.getString(Constants.FIREBASE_FIELD_LAST_NAME),
                           value.getString(Constants.FIREBASE_FIELD_EMAIL),
                           value.getString(Constants.FIREBASE_FIELD_BALANCE),
                           value.get(Constants.FIREBASE_FIELD_MOVES) as ArrayList<HashMap<String,String>>
                       )
                        cont.resume(Result.Success(user))
                    }
                })

            }catch (e:Exception){
                cont.resume(Result.Error(e))
            }
        }


}


