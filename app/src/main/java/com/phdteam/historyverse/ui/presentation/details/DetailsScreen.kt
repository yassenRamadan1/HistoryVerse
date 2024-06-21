package com.phdteam.historyverse.ui.presentation.details

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVAppBar
import com.phdteam.historyverse.ui.presentation.details.components.ArtifatsTab
import com.phdteam.historyverse.ui.presentation.details.components.ProductsTab
import com.phdteam.historyverse.ui.presentation.details.components.ReviewTab
import com.phdteam.historyverse.ui.theme.Theme
import com.phdteam.historyverse.ui.theme.starColor
import com.phdteam.historyverse.ui.theme.yellowColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

//import com.phdteam.historyverse.R

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    DetailsScreenContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DetailsScreenContent(state: MuseumDetailsUiState, viewModel: DetailsViewModel) {
    val color = Theme.colors
    val list = listOf("Reviews", "Artifacts", "Products")
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        3
    }
    val selectedTab = remember { mutableIntStateOf(pagerState.currentPage) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(topBar = {
        HVAppBar(
            title = state.museam
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(it)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Theme.colors.background)
                ) {
                    val (tabLayout, description, imageBox, bookButton, pager, pagerContent) = createRefs()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.4f)
                            .constrainAs(imageBox) {
                                top.linkTo(parent.top)
                            }) {
                        Image(
//                            painter = rememberAsyncImagePainter(state.imageUrl) ,
                            painter = painterResource(R.drawable.museum_img),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),

                                ) {
                                Text(
                                    state.museam,
                                    color = Color.White,
                                    style = Theme.typography.titleSmall
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 4.dp)
                                        .align(Alignment.CenterVertically),
                                    tint = starColor
                                )
                                Text(
                                    state.rating.toString(),
                                    color = Color.White,
                                    style = Theme.typography.titleSmall
                                )
                            }

                        }
                    }
                    Text(
                        state.description,
                        modifier = Modifier
                            .constrainAs(description) {
                                top.linkTo(imageBox.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        style = Theme.typography.bodyMedium
                    )
                    Box(
                        modifier = Modifier
                            .constrainAs(bookButton) {
                                end.linkTo(parent.end)
                                bottom.linkTo(imageBox.bottom, margin = (-24).dp)
                            }
                            .padding(end = 16.dp)
                            .clip(CircleShape)
                            .clickable { viewModel.onBookClick() }
                            .background(yellowColor)
                            .padding(16.dp),
                    ) {
                        Text(
                            "Book",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            color = Color.White
                        )
                    }


                    ScrollableTabRow(
                        modifier = Modifier
                            .constrainAs(tabLayout) {
                                top.linkTo(description.bottom, margin = (16).dp)
                            }
                            .wrapContentHeight(),
                        selectedTabIndex = selectedTab.value,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier
                                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                    .padding(start = 16.dp, end = 16.dp), color = yellowColor
                            )
                        },
                        containerColor = Color.Transparent,
                        divider = {},
                    ) {
                        list.forEachIndexed { index, item ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(
                                            index, animationSpec = tween(200)
                                        )
                                    }
                                },
                                selectedContentColor = yellowColor,
                                unselectedContentColor = Color.Black
                            ) {
                                Text(
                                    text = item, modifier = Modifier.padding(
                                        bottom = 8.dp, start = 4.dp, end = 4.dp
                                    )
                                )
                            }
                        }
                    }
//                    val modifier = Modifier
//                        .constrainAs(pagerContent) {
//                            top.linkTo(pager.top,)
//                            bottom.linkTo(pager.bottom)
//                        }
//                        .padding(horizontal = 16.dp)
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .constrainAs(pager) {
                                top.linkTo(tabLayout.bottom, margin = 16.dp)
//                                bottom.linkTo(parent.bottom)
                            }
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                        beyondBoundsPageCount = 2,
                    ) { page: Int ->
                        when (page) {
                            0 -> {
                                ReviewTab(
                                    modifier = Modifier, state = state,
                                    viewModel::onMakeReview
                                )
                            }

                            1 -> {
                                ArtifatsTab(
                                    modifier = Modifier, state = state,
                                    onFavoriteClick = viewModel::onFavoriteClick,
                                    onClickArtifactCard = viewModel::onArtifactClick
                                )

                            }

                            2 -> {
                                ProductsTab(
                                    modifier = Modifier,
                                    state = state,
                                    onFavoriteClick = viewModel::onFavoriteClick,
                                    onCardClick = viewModel::onProductClick
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun preview() {
//    DetailsScreenContent(DetailsUiState(museam = "Alexandria Museum" , isLoading = true))
//}