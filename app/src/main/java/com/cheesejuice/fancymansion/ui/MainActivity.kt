package com.cheesejuice.fancymansion.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.cheesejuice.fancymansion.ui.nav.FancyMansionNavHost
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FancyMansionApp()
        }
    }
}
@Composable
fun FancyMansionApp(){
    FancyMansionTheme {
        val navController = rememberNavController()
        FancyMansionNavHost(navController = navController)
    }
}