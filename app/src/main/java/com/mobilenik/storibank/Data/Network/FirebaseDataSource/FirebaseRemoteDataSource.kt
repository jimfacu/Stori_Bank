package com.mobilenik.storibank.Data.Network.FirebaseDataSource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import kotlin.collections.HashMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.mobilenik.storibank.Data.Result
import com.mobilenik.storibank.Utils.StoriBankPreferences

class FirebaseRemoteDataSource : FirebaseDataSource {

    private var loader = FirebaseAuth.getInstance()
    private var referenceDataBase = FirebaseDatabase.getInstance().getReference(Constants.REFERENCE_DATABASE_USERS)
    private var documentReference = FirebaseFirestore.getInstance()



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
                    cont.resume(Result.Success("Imagen subida con exito"))
                }
                    .addOnFailureListener{
                        cont.resume(Result.Error(it))
                    }

            }catch (e:Exception){
                cont.resume(Result.Error(e))
        }
    }


}