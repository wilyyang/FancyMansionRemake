package com.cheesejuice.fancymansion.data.repository

import android.content.Context
import com.cheesejuice.fancymansion.data.source.remote.firebase.FirebaseSource
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val context: Context) {
    private val firebaseSource: FirebaseSource = FirebaseSource()

    fun googleSignIn(account : GoogleSignInAccount) : Boolean{
        CoroutineScope(Dispatchers.IO).launch{
            firebaseSource.googleSignIn(account)
        }
        return false
    }

    private fun initUserInfo(){
    }

}