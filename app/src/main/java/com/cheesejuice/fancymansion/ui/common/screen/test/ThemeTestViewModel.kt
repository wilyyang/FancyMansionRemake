package com.cheesejuice.fancymansion.ui.common.screen.test

import android.content.Context
import com.cheesejuice.fancymansion.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ThemeTestViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : BaseViewModel(){
    fun showTestLoading(message : String? = null) {
        showLoading(
            message = message,
            onDismiss = { dismissDialog() }
        )
    }
}