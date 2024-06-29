package com.phdteam.historyverse.ui.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVArtifact
import com.phdteam.historyverse.ui.components.HVCategory
import com.phdteam.historyverse.ui.components.HVMuseum
import com.phdteam.historyverse.ui.components.HVTitleWithSeeAll
import com.phdteam.historyverse.ui.presentation.home.component.ChatBot
import com.phdteam.historyverse.ui.presentation.home.component.HVAutoSlidingCarousel
import com.phdteam.historyverse.ui.presentation.home.component.HomeAppBar
import com.phdteam.historyverse.ui.presentation.seeall.SeeAllType
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateTo: (HomeUIEffect) -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    HomeContent(state = state, onNavigateTo = navigateTo)

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: HomeUIEffect?, context: Context) {

    when (effect) {
        HomeUIEffect.HomeError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeContent(
    state: HomeUIState,
    onNavigateTo: (HomeUIEffect) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        if (state.isLoading) {
            CircularProgressIndicator(color = Theme.colors.primary)
        } else {
            HomeAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                onMarketClicked = { onNavigateTo(HomeUIEffect.NavigateToMarket) }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {

                ChatBot(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { onNavigateTo(HomeUIEffect.NavigateToChatBoot) }
                )

                Card(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    HVAutoSlidingCarousel(
                        itemsCount = state.advertisement.size,
                        itemContent = { index ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(state.advertisement[index].imageUrl)
                                    .build(),
                                contentDescription = state.advertisement[index].description,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(200.dp)
                                    .fillMaxWidth()
                            )
                        }
                    )
                }
                HVTitleWithSeeAll(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 10.dp)
                        .padding(horizontal = 16.dp),
                    title = stringResource(id = R.string.categories),
                    showSeeAll = false,
                    onClick = {}
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.categories) { category ->
                        HVCategory(
                            modifier = Modifier.width(100.dp),
                            name = category ?: "",
                            onClick = {}
                        )
                    }
                }
                if (state.museums.isNotEmpty()) {
                    HVTitleWithSeeAll(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 10.dp)
                            .padding(horizontal = 16.dp),
                        title = stringResource(id = R.string.Museums),
                        showSeeAll = state.museums.showSeeAll(),
                        onClick = { onNavigateTo(HomeUIEffect.NavigateToSeeAll(SeeAllType.Museums)) }
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.museums) { museum ->
                        HVMuseum(
                            modifier = Modifier.size(height = 215.dp, width = 322.dp),
                            name = museum.name,
                            address = museum.city,
                            imageUrl = museum.imageUrl,
                            onClick = { onNavigateTo(HomeUIEffect.NavigateToDetail(museum.id)) }
                        )
                    }
                }

                HVTitleWithSeeAll(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .padding(horizontal = 16.dp),
                    title = stringResource(id = R.string.artifacts),
                    showSeeAll = state.artifacts.showSeeAll(),
                    onClick = { onNavigateTo(HomeUIEffect.NavigateToSeeAll(SeeAllType.Artifacts)) }
                )
                if (state.artifacts.isNotEmpty()) {
                    state.artifacts.take(4).forEach { artifact ->
                        HVArtifact(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .padding(horizontal = 16.dp),
                            name = artifact.name,
                            rate = 4.0,
                            numberReviewers = 500,
                            profileUrl = artifact.imageUrl,
                            onClick = { onNavigateTo(HomeUIEffect.NavigateToDetail(artifact.id)) }
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_data),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}