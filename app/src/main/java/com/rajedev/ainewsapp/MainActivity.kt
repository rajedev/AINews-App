package com.rajedev.ainewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rajedev.ainewsapp.presentation.navigation.AppNavHost
import com.rajedev.ainewsapp.ui.theme.AINewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AINewsAppTheme {
                AppNavHost()
            }
        }
    }
}
