package com.phdteam.historyverse.ui.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phdteam.historyverse.ui.presentation.auth.signin.SignInScreen
import com.phdteam.historyverse.ui.presentation.auth.welcome.WelcomeScreen
import com.phdteam.historyverse.ui.presentation.auth.welcome.WelcomeUiEffect
import com.phdteam.historyverse.ui.presentation.favorite.FavoriteScreen
import com.phdteam.historyverse.ui.presentation.home.HomeScreen
import com.phdteam.historyverse.ui.presentation.auth.login.LoginScreen
import com.phdteam.historyverse.ui.presentation.main.MainScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.ext.navigateTo
import com.phdteam.historyverse.ui.presentation.main.navigation.graph.MainNavGraph
import com.phdteam.historyverse.ui.presentation.profile.ProfileScreen
import com.phdteam.historyverse.ui.presentation.search.SearchScreen


fun NavGraphBuilder.loginNavGraph(onNavigateToRoot: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Login.route
    ) {
        LoginScreen(
            navigateTo = {
                Screen.Main.withClearBackStack().also(onNavigateToRoot)
            },
            onNavigateBack = { onNavigateBack() }
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

fun NavGraphBuilder.welcomeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Welcome.route
    )
    {
        WelcomeScreen() {
            when (it) {
                WelcomeUiEffect.OnClickLogin -> Screen.Login.withClearBackStack().also(onNavigateTo)
                WelcomeUiEffect.OnClickSignIn -> Screen.SignIn.withClearBackStack()
                    .also(onNavigateTo)

                else -> {}
            }
        }
    }
}

fun NavGraphBuilder.signInScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.SignIn.route
    ) {
        SignInScreen(
            navigateTo = {
                Screen.Main.withClearBackStack().also(onNavigateTo)
            },
            onNavigateBack = { onNavigateBack() }
        )
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

    }
}