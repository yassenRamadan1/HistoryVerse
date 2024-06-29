package com.phdteam.historyverse.ui.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phdteam.historyverse.ui.presentation.auth.login.LoginScreen
import com.phdteam.historyverse.ui.presentation.auth.signin.SignInScreen
import com.phdteam.historyverse.ui.presentation.auth.welcome.WelcomeScreen
import com.phdteam.historyverse.ui.presentation.auth.welcome.WelcomeUiEffect
import com.phdteam.historyverse.ui.presentation.cart.CartScreen
import com.phdteam.historyverse.ui.presentation.chatbot.ChatBotScreen
import com.phdteam.historyverse.ui.presentation.details.DetailsScreen
import com.phdteam.historyverse.ui.presentation.details.DetailsUiEffect
import com.phdteam.historyverse.ui.presentation.favorite.FavoriteScreen
import com.phdteam.historyverse.ui.presentation.home.HomeScreen
import com.phdteam.historyverse.ui.presentation.home.HomeUIEffect
import com.phdteam.historyverse.ui.presentation.main.MainScreen
import com.phdteam.historyverse.ui.presentation.main.navigation.ext.navigateTo
import com.phdteam.historyverse.ui.presentation.main.navigation.graph.MainNavGraph
import com.phdteam.historyverse.ui.presentation.market.MarketScreen
import com.phdteam.historyverse.ui.presentation.market.MarketUiEffect
import com.phdteam.historyverse.ui.presentation.market.marketDetails.MarketDetailsUiEffect
import com.phdteam.historyverse.ui.presentation.market.marketDetails.MarketItemDetailsScreen
import com.phdteam.historyverse.ui.presentation.payment.PaymentScreen
import com.phdteam.historyverse.ui.presentation.profile.ProfileScreen
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIEffect
import com.phdteam.historyverse.ui.presentation.rate.RateScreen
import com.phdteam.historyverse.ui.presentation.search.SearchScreen
import com.phdteam.historyverse.ui.presentation.search.SearchUIEffect
import com.phdteam.historyverse.ui.presentation.seeall.SeeAllScreen
import com.phdteam.historyverse.ui.presentation.seeall.toSeeAllType
import com.phdteam.historyverse.ui.presentation.ticket.TicketScreen
import com.phdteam.historyverse.ui.presentation.tickets.TicketsScreen
import com.phdteam.historyverse.ui.presentation.trip.TripScreen


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
                    Screen.Trip,
                    Screen.Profile,
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
    composable(route = Screen.Home.route) {
        HomeScreen(
            navigateTo = { navigate ->
                when (navigate) {
                    HomeUIEffect.NavigateToChatBoot -> Screen.ChatBot.also(onNavigateTo)
                    is HomeUIEffect.NavigateToMarket -> Screen.Market.also(onNavigateTo)
                    is HomeUIEffect.NavigateToDetail -> {
                        Screen.Details.args = bundleOf(Pair("id", navigate.id))
                        Screen.Details.also(onNavigateTo)
                    }

                    is HomeUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", navigate.type.value))
                        Screen.SeeAll.also(onNavigateTo)
                    }

                    is HomeUIEffect.NavigateToArtifactDetails -> {
                        Screen.MarketItemDetails.args = bundleOf(Pair("id", navigate.id))
                        Screen.MarketItemDetails.also(onNavigateTo)
                    }

                    else -> {
                        Screen.Details.also(onNavigateTo)
                    }
                }
            },
        )
    }
}

fun NavGraphBuilder.chatBotScreen(onNavigateBack: () -> Unit) {
    composable(
        route = Screen.ChatBot.route
    ) {
        ChatBotScreen(onNavigateBack = onNavigateBack)
    }
}

fun NavGraphBuilder.tripScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Trip.route
    ) {
        TripScreen(
            navigateTo = {
                Screen.Details.args = bundleOf(Pair("id", it))
                Screen.Details.also(onNavigateTo)
            },
        )
    }
}

fun NavGraphBuilder.tripScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Trip.route
    ) {
        TripScreen(
            navigateTo = {
                Screen.Main.withClearBackStack().also(onNavigateTo)
            },
        )
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

                WelcomeUiEffect.onSignedIn -> Screen.Main.withClearBackStack().also(onNavigateTo)

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

        SearchScreen(
            onNavigateTo = { effect ->
                when (effect) {
                    is SearchUIEffect.NavigateToDetails -> {
                        Screen.MarketItemDetails.args = bundleOf(Pair("id", effect.id))
                        Screen.MarketItemDetails.also(onNavigateTo)
                    }

                    else -> {}
                }

            }
        )
    }
}

fun NavGraphBuilder.profileScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Profile.route
    ) {

        ProfileScreen(
            onNavFavorite = {
                Screen.Favorite.also(onNavigateTo)
            },
            onNavigateTo = { effect ->
                when (effect) {
                    is ProfileUIEffect.NavigateToCart -> Screen.Cart.also(onNavigateTo)
                    else -> {}
                }
            },
            onNavCart = {
                Screen.Cart.also(onNavigateTo)
            },
            onNavTickets = {
                Screen.Tickets.also(onNavigateTo)
            }

        )
    }
}

fun NavGraphBuilder.favoriteScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Favorite.route
    ) {
        FavoriteScreen(
            onClickCard = { Screen.Details.also(onNavigateTo) },
            onNavigateBack = { onNavigateBack() }
        )


    }
}

fun NavGraphBuilder.detailsScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Details.route
    ) {
        val id = Screen.Details.args?.getInt("id")
        DetailsScreen(
            id = id,
            onNavigateBack = onNavigateBack,
            navigateTo = { effect ->
                when (effect) {
                    is DetailsUiEffect.NavigateToProductDetails -> {
                        Screen.MarketItemDetails.args = bundleOf(Pair("id", effect.itemId))
                        Screen.MarketItemDetails.also(onNavigateTo)
                    }

                    is DetailsUiEffect.NavigateToArtifactDetails -> {
                        Screen.Details.args = bundleOf(Pair("id", effect.itemId))
                        Screen.Details.also(onNavigateTo)
                    }

                    else -> {}
                }
            },
            onBookClick = {
                Screen.Ticket.args = bundleOf(Pair("id", 0))
                Screen.Ticket.also(onNavigateTo)
            }

        )
    }
}

fun NavGraphBuilder.onSeeAllScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    this.composable(
        route = Screen.SeeAll.route
    ) {
        val value = Screen.SeeAll.args?.getString("type").toString().toSeeAllType()
        SeeAllScreen(
            type = value,
            navigateTo = {
                Screen.Details.args = bundleOf(Pair("id", it))
                Screen.Details.also(onNavigateTo)
            },
            navigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.marketScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Market.route
    ) {
        MarketScreen { effect ->
            when (effect) {
                is MarketUiEffect.NavigateToItemDetails -> {
                    Screen.MarketItemDetails.args = bundleOf(Pair("id", effect.id))
                    Screen.MarketItemDetails.also(onNavigateTo)

                }

                else -> {}
            }
        }
    }
}

fun NavGraphBuilder.marketItemDetailsScreen(
    onNavigateTo: (Screen) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(
        route = Screen.MarketItemDetails.route,
    ) {
        val value = Screen.MarketItemDetails.args?.getInt("id")

        MarketItemDetailsScreen(
            navigateTo = { effect ->
                when (effect) {
                    is MarketDetailsUiEffect.NavigateToMarketDetails -> {
                        Screen.MarketItemDetails.args = bundleOf(Pair("id", id))
                        Screen.MarketItemDetails.also(onNavigateTo)
                    }

                    is MarketDetailsUiEffect.NavigateToReview -> {
                        Screen.Review.args = bundleOf(Pair("id", id))
                        Screen.Review.also(onNavigateTo)
                    }

                    else -> {}
                }
            },
            onNavigateBack = {
                onNavigateBack()
            },
            itemId = value
        )
    }
}

fun NavGraphBuilder.ratingScreen(onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Review.route,
    ) {
        val id = Screen.Review.args?.getInt("id")
        RateScreen(
            navigateBack = onNavigateBack,
            itemId = id
        )
    }
}

fun NavGraphBuilder.paymentScreen(onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Payment.route,
    ) {
        PaymentScreen(
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavGraphBuilder.cartScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(route = Screen.Cart.route) {
        CartScreen(
            onNavigate = {
                Screen.Payment.also(onNavigateTo)
            },
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavGraphBuilder.ticketsScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Tickets.route,
    ) {
        TicketsScreen(
            onNavigateBack = onNavigateBack,
            onNavigateTicket = {
                Screen.Ticket.args = bundleOf(Pair("id", it))
                Screen.Ticket.also(onNavigateTo)
            }
        )
    }
}

fun NavGraphBuilder.ticketScreen(onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Ticket.route,
    ) {
        val id = Screen.Ticket.args?.getInt("id")
        TicketScreen(
            id = id,
            onNavigateBack = onNavigateBack
        )
    }
}


