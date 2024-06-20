package com.phdteam.historyverse.ui.presentation.market

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVBottomSheet
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.market.components.MarketBottomSheetContent
import com.phdteam.historyverse.ui.presentation.market.components.MarketProductItem
import com.phdteam.historyverse.ui.presentation.market.components.SelectedFilterItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(
    viewModel: MarketViewModel = koinViewModel(),
    navigateTo: (MarketUiEffect?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val snackBarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = {
            true
        }
    )
    MarketContent(uiState, viewModel, sheetState)

    LaunchedEffect(key1 = uiState.isLoading) {
        if (uiState.errorMessage != null && uiState.isError) {
            val result = snackBarHostState.showSnackbar(
                message = uiState.errorMessage!!,
                actionLabel = "Hide",
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.Dismissed || result == SnackbarResult.ActionPerformed) {
                viewModel.clearErrorState()
            }
        }
        viewModel.effect.collectLatest {
            onEffect(effect, navigateTo)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketContent(uiState: MarketUiState, viewModel: MarketViewModel, sheetState: SheetState) {
    LaunchedEffect(key1 = uiState.searchText) {
        delay(300)
        viewModel.search()
    }

    LaunchedEffect(key1 = uiState.isSheetVisible) {
        sheetState.apply { (if (uiState.isSheetVisible) expand() else hide()) }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = 16.dp)
    ) {
        if (uiState.isLoading)
            CircularProgressIndicator()
        else {
            if (uiState.isSheetVisible)
                HVBottomSheet(
                    onDismissRequest = { viewModel.closeBottomSheet() },
                    state = sheetState
                ) {
                    MarketBottomSheetContent(
                        filters = uiState.filters,
                        onFilterClick = viewModel::onFilterClick
                    )
                }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .background(color = Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = uiState.searchText,
                    onValueChange = {
                        viewModel.updateSearchText(
                            it
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_icon),
                            contentDescription = null,
                            modifier = Modifier.noRippleEffect {
                                viewModel.openBottomSheet()
                            }
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.noRippleEffect { viewModel.search() })

                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                )
                AnimatedVisibility(visible = uiState.selectedFilters.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(start = 16.dp)
                    ) {
                        items(uiState.selectedFilters.size) { index ->
                            SelectedFilterItem(
                                uiState.selectedFilters[index],
                                onFilterClick = viewModel::onFilterClick
                            )
                        }

                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(uiState.items.size) { index ->
                        val item = uiState.items[index]
                        MarketProductItem(item)
                    }
                }
            }


        }
    }
}


private fun onEffect(
    effect: MarketUiEffect?,
    navigateTo: (MarketUiEffect?) -> Unit
) {
    when (effect) {
        MarketUiEffect.NavigateToItemDetails -> navigateTo(MarketUiEffect.NavigateToItemDetails)
//        MarketUiEffect.OpenBottomSheet ->
        else -> {}
    }
}