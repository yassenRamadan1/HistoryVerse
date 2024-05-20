package com.phdteam.historyverse.ui.presentation.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.phdTeam.HistoryVerse.R


const val navigationRouteLogin = "login"
const val navigationRouteChatBot = "chatBot"
const val navigationRouteNotification = "notification"
const val navigationRouteSeeAll = "seeAll"
const val navigationRouteWelcome = "welcome"
const val navigationRouteSignIn = "sign_in"
const val navigationRouteMain = "main"
const val navigationRouteHome = "home"
const val navigationRouteSearch = "search"
const val navigationRouteProfile = "profile"
const val navigationRouteVedio = "review"
const val navigationRouteFavorite = "favorite"
const val navigationRouteDetails = "details"


sealed class Screen(
    val route: String,
    var routePath: String? = null,
    var clearBackStack: Boolean = false,
    val restoreState: Boolean = true,
    val title: Int = 0,
    val icon: ImageVector? = null
) {
    fun withClearBackStack() = apply { clearBackStack = true }

    fun routeWith(path: String) = apply {
        routePath = path
    }

    data object Login : Screen(navigationRouteLogin)
    data object Welcome : Screen(navigationRouteWelcome)

    data object SignIn : Screen(navigationRouteSignIn)

    data object Main : Screen(navigationRouteMain)
    data object Home : Screen(
        route = navigationRouteHome,
        title = R.string.home_title,
        icon = Icons.Rounded.Home
    )
    data object Search : Screen(
        route = navigationRouteSearch,
        title = R.string.search_title,
        icon = Icons.Rounded.Search
    )
    data object Profile : Screen(
        route = navigationRouteProfile,
        title = R.string.profile_title,
        icon = Icons.Rounded.Person
    )

    data object Details : Screen(
        route = navigationRouteDetails ,
    )
    data object Favorite : Screen(
        route = navigationRouteFavorite,
        title = R.string.favorite_title,
    )
}