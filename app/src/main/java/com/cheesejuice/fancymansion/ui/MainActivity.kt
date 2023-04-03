package com.cheesejuice.fancymansion.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cheesejuice.fancymansion.ui.common.screen.ThemeTestScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeTestScreen()
        }
    }
}