package com.phdteam.historyverse.ui.presentation.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.phdteam.historyverse.ui.presentation.main.navigation.*
import com.phdteam.historyverse.ui.presentation.main.navigation.Screen
import com.phdteam.historyverse.ui.presentation.main.navigation.favoriteScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.homeScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.profileScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.searchScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigateToRoot: (Screen) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {

        homeScreen(onNavigateToRoot)
        searchScreen(onNavigateToRoot)
        tripScreen(onNavigateToRoot)
        profileScreen(onNavigateToRoot)


    }
}