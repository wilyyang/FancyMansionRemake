package com.cheesejuice.fancymansion.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cheesejuice.core.ui.theme.FancyMansionTheme
import com.cheesejuice.fancymansion.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FancyMansionTheme {
                AppNavigation()
            }
        }
    }
}