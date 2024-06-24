package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.components.ItemCard
import com.phdteam.historyverse.ui.presentation.details.DetailsScreenUiState
@Composable
fun ArtifatsTab(
    modifier: Modifier = Modifier,
    state: DetailsScreenUiState,
    onClickArtifactCard: (cardId: String) -> Unit,
    onFavoriteClick: (cardId: String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Recommended Artifacts",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.recommendedArtifacts.size) { index ->
                        ItemCard(
                            state = state.recommendedArtifacts[index],
                            onClickCard = onClickArtifactCard,
                            onClickFavorite = onFavoriteClick
                        )
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    "Most popular",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.mostPopularArtifacts.size) { index ->
                        ItemCard(
                            state = state.mostPopularArtifacts[index],
                            onClickCard = onClickArtifactCard,
                            onClickFavorite = onFavoriteClick
                        )
                    }

                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))

        }

    }

}