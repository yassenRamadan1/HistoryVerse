package com.phdteam.historyverse.ui.presentation.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.phdteam.historyverse.ui.presentation.main.navigation.Screen
import com.phdteam.historyverse.ui.presentation.main.navigation.cartScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.chatBotScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.detailsScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.ext.navigateTo
import com.phdteam.historyverse.ui.presentation.main.navigation.favoriteScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.loginNavGraph
import com.phdteam.historyverse.ui.presentation.main.navigation.mainNavGraph
import com.phdteam.historyverse.ui.presentation.main.navigation.marketItemDetailsScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.marketScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.onSeeAllScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.profileScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.ratingScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.searchScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.signInScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.welcomeScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        route = "root_host",
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        onSeeAllScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        tripScreen(
            navController::navigateTo,
        )
        mainNavGraph(onNavigateToRoot = navController::navigateTo)
        detailsScreen(
            navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        profileScreen(onNavigateTo = navController::navigateTo)

        mainNavGraph(onNavigateToRoot = navController::navigateTo)
        detailsScreen(
            navController::navigateTo,
            navController::navigateUp
        )
        profileScreen(onNavigateTo = navController::navigateTo)

        loginNavGraph(
            onNavigateToRoot = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        mainNavGraph(onNavigateToRoot = navController::navigateTo)
        welcomeScreen(onNavigateTo = navController::navigateTo)
        signInScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        marketScreen(
            onNavigateTo = navController::navigateTo,
        )
        marketItemDetailsScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        ratingScreen(
            onNavigateBack = navController::navigateUp
        )
        chatBotScreen(onNavigateBack = navController::navigateUp)
        searchScreen(onNavigateTo = navController::navigateTo)
        favoriteScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        cartScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
    }
}