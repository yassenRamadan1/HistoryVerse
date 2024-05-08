package com.phdteam.historyverse.ui.presentation.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.phdteam.historyverse.ui.presentation.main.navigation.*

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigateToRoot: (Screen) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Details.route,
        modifier = modifier,
    ) {

        homeScreen(onNavigateToRoot)
        searchScreen(onNavigateToRoot)
        profileScreen(onNavigateToRoot)
        detailsScreen(onNavigateToRoot)
    }
}