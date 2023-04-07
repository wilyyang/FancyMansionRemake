package com.cheesejuice.fancymansion.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){
    val isSkim: MutableState<Boolean> = mutableStateOf(false)
    val skimScreen: MutableState<@Composable () -> Unit> = mutableStateOf(
        @Composable{

        }
    )

    fun skimHide(){
        isSkim.value = false
    }

    fun skimShow(skimScreen: @Composable () -> Unit){
        isSkim.value = true
        this.skimScreen.value = skimScreen
    }
}