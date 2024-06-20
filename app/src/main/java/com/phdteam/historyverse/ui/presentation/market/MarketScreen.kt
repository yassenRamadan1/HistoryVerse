package com.phdteam.historyverse.ui.presentation.market

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun MarketScreen(viewModel: MarketViewModel = koinViewModel()) {
    val uiState by viewModel.state.collectAsState()
    MarketContent(uiState, viewModel)
}

@Composable
fun MarketContent(uiState: MarketUiState, viewModel: MarketViewModel) {
    LaunchedEffect(key1 = uiState.searchText) {
        delay(300)
        viewModel.search(uiState.searchText)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = 16.dp)
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
                .padding(vertical = 16.dp)
                .clip(RoundedCornerShape(16.dp)),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)

            }
        )

    }
}
