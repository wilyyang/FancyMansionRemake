package com.cheesejuice.fancymansion.ui.content.user.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.data.repository.UserRepository
import com.cheesejuice.fancymansion.ui.common.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@ApplicationContext val context : Context) : BaseViewModel(){

    fun getGoogleSignIntent() : Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso).signInIntent
    }

    fun resultForGoogleSignDialog(result : ActivityResult){
        if(result.resultCode == -1){
            showLoading(message = "로그인 중 입니다.")
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            // userRepository.googleSignIn(task.getResult(ApiException::class.java))
        }
    }
}