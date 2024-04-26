package com.phdteam.historyverse.ui.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.phdteam.historyverse.ui.presentation.main.navigation.Screen
import com.phdteam.historyverse.ui.presentation.main.navigation.graph.RootNavGraph

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App() {
    val navController = rememberNavController()

    RootNavGraph(
        navController = navController,
        startDestination = Screen.Login
    )
}