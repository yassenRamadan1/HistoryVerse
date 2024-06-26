package com.phdteam.historyverse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.FirebaseApp
import com.phdteam.historyverse.ui.presentation.main.App
import com.phdteam.historyverse.ui.theme.MindfulMentorTheme
import com.phdteam.historyverse.ui.theme.Theme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MindfulMentorTheme {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Theme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}