package com.phdteam.historyverse.ui.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.phdteam.historyverse.ui.presentation.main.navigation.Screen
import com.phdteam.historyverse.ui.presentation.main.navigation.graph.RootNavGraph
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = koinViewModel()
    val isSignedIn by mainViewModel.isSignedIn.collectAsState()
    RootNavGraph(
        navController = navController,
        startDestination = if (isSignedIn) Screen.Main else Screen.Welcome
    )
}