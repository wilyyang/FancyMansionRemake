package com.cheesejuice.fancymansion.ui.content.home

import com.cheesejuice.fancymansion.ui.common.BaseViewModel
import com.cheesejuice.fancymansion.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel(){
    init {
        _uiState.value = UiState.Loading()

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000L)

            _uiState.value = UiState.Loaded()
        }
    }

}