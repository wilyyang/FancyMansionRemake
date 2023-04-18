package com.cheesejuice.fancymansion.data.source.remote.firebase

import com.cheesejuice.fancymansion.data.repository.UserRepository
import com.cheesejuice.fancymansion.module.log.Log
import com.cheesejuice.fancymansion.module.log.LogType
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseSource {
    companion object {
        val auth : FirebaseAuth by lazy { Firebase.auth }
    }

    suspend fun googleSignIn(account : GoogleSignInAccount) : Boolean{
        try {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            val result =  auth.signInWithCredential(credential).await()
        } catch (e: ApiException) {
            e.printStackTrace()
            Log.send(message = e.message, type = LogType.E)
        }
        return false
    }
}