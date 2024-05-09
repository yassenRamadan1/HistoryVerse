package com.phdteam.historyverse.ui.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phdteam.historyverse.ui.presentation.favorite.FavoriteScreen
import com.phdteam.historyverse.ui.presentation.home.HomeScreen
import com.phdteam.historyverse.ui.presentation.login.LoginScreen
import com.phdteam.historyverse.ui.presentation.main.MainScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.ext.navigateTo
import com.phdteam.historyverse.ui.presentation.main.navigation.graph.MainNavGraph
import com.phdteam.historyverse.ui.presentation.profile.ProfileScreen
import com.phdteam.historyverse.ui.presentation.search.SearchScreen


fun NavGraphBuilder.loginNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Login.route
    ) {

        LoginScreen(
            navigateTo = {
                Screen.Main.withClearBackStack().also(onNavigateToRoot)
            }
        )
    }
}


fun NavGraphBuilder.mainNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Main.route
    ) {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val bottomBar: @Composable () -> Unit = {
            HRBottomNavigation(
                screens = listOf(
                    Screen.Home,
                    Screen.Search,
                    Screen.Profile
                ), onNavigateTo = navController::navigateTo,
                currentDestination = navBackStackEntry?.destination
            )
        }

        val nestedNavGraph: @Composable () -> Unit = {
            MainNavGraph(
                navController = navController,
                onNavigateToRoot = onNavigateToRoot
            )
        }

        MainScreen(
            bottomBar = bottomBar,
            nestedNavGraph = nestedNavGraph
        )
    }

}


fun NavGraphBuilder.homeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Home.route
    ) {

        HomeScreen()
    }
}

fun NavGraphBuilder.searchScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Search.route
    ) {

        SearchScreen()
    }
}

fun NavGraphBuilder.profileScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Profile.route
    ) {

        ProfileScreen(
            onNavFavorite = {
                Screen.Favorite.also(onNavigateTo)
            }
        )
    }
}

fun NavGraphBuilder.favoriteScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Favorite.route
    ) {
        FavoriteScreen()
    }
}