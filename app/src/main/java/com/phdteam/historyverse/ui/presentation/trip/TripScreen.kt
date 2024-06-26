package com.phdteam.historyverse.ui.presentation.trip

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVAppBar
import com.phdteam.historyverse.ui.components.HVButton
import com.phdteam.historyverse.ui.components.HVChip
import com.phdteam.historyverse.ui.components.HVMuseum
import com.phdteam.historyverse.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripScreen(
    viewModel: TripViewModel = koinViewModel(),
    navigateTo: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    TripContent(
        state = state,
        onClickChip = viewModel::onClickChip,
        onClickMakeTrip = viewModel::onClickMakeTrip,
        onNavigateToMuseum = navigateTo
    )
}

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
private fun TripContent(
    state: TripUiState,
    onClickChip: (city: String) -> Unit,
    onClickMakeTrip: () -> Unit,
    onNavigateToMuseum: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val context = LocalContext.current

        if (state.isLoading) {
            CircularProgressIndicator(color = Theme.colors.primary)
        } else {
            HVAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Make your trip",
                onBack = {},
                iconColor = Color.Transparent
            )
            FlowRow(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                maxItemsInEachRow = 4,
            ) {
                state.egyptGovernments.forEach { governorate ->
                    HVChip(
                        city = governorate,
                        isSelected = state.targetCities.contains(governorate),
                        onSelectedChanged = { onClickChip(governorate) },
                    )
                }
            }
            HVButton(
                title = "Make my trip",
                onClick = {
                    if (state.targetCities.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Please select at least one city",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        onClickMakeTrip()
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 16.dp)
            )
            if (state.museums.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(state.museums) { museum ->
                        HVMuseum(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height = 215.dp),
                            name = museum.name,
                            address = museum.city,
                            imageUrl = museum.imageUrl,
                            onClick = {
                                onNavigateToMuseum()
                            }
                        )
                    }
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.history_trip),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}