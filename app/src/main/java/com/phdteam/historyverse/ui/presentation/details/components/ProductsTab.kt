package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.phdteam.historyverse.ui.presentation.favorite.CardType

@Composable
fun ProductsTab(
    modifier: Modifier = Modifier,
    state: DetailsScreenUiState,
    onCardClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Text(
                "Recommended Products",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.recommendedArtifacts.size) { index ->
                    ItemCard(
                        state = state.recommendedArtifacts[index],
                        onClickCard = onCardClick,
                        onClickFavorite = onFavoriteClick,
                        cardType = CardType.MUSEUM
                    )
                }
            }
        }
        item {
            Text(
                "Most Sold",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                modifier= Modifier.padding(start = 16.dp)
            )

        }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.mostPopularArtifacts.size) { index ->
                    ItemCard(
                        state = state.mostPopularArtifacts[index],
                        onClickCard = onCardClick,
                        onClickFavorite = onFavoriteClick,
                        cardType = CardType.MUSEUM
                    )
                }

            }

        }
        item {
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}