package com.cheesejuice.fancymansion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.cheesejuice.core.ui.theme.FancyMansionTheme
import com.cheesejuice.fancymansion.nav.FancyMansionNavHost
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