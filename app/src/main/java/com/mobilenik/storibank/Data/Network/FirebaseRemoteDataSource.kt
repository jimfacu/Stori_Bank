package com.mobilenik.storibank.Data.Network

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Data.Model.Moves
import com.mobilenik.storibank.Data.Model.UserInformation
import com.mobilenik.storibank.Data.Model.UserRegister
import org.checkerframework.checker.units.qual.K
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRemoteDataSource : FirebaseDataSource{

    private var loader = FirebaseAuth.getInstance()
    private var referenceDataBase = FirebaseDatabase.getInstance().getReference(Constants.REFERENCE_DATABASE_USERS)
    private var documentReference = FirebaseFirestore.getInstance()


    override suspend fun userRegistration(body:UserRegister): Result<UserInformation> =

        suspendCoroutine { cont ->

            try {
                loader.createUserWithEmailAndPassword(body.email,body.password).addOnCompleteListener {
                    if(it.isSuccessful){
                        var hp = HashMap<String, String>()
                        hp.put("Nombre",body.name)
                        hp.put("Apellido",body.lastName)
                        hp.put("Email",body.email)
                        hp.put("Balance","100000")
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



}