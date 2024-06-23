package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.details.MuseumDetailsUiState
import com.phdteam.historyverse.ui.presentation.details.ReviewTabState
import com.phdteam.historyverse.ui.theme.Theme
import com.phdteam.historyverse.ui.theme.yellowColor

@Composable
fun ReviewTab(
    state: MuseumDetailsUiState,
    onReview: (itemId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {

        item {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Review This Place",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 1..5) {
                        Icon(
                            painter = painterResource(id = if (i <= state.reviewState.review - 1) R.drawable.star_smooth else R.drawable.star_empty),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .noRippleEffect { onReview(state.museumId) },
                            tint = yellowColor
                        )
                    }
                }
            }
        }
        item {
            Text(
                "Reviews",
                style = Theme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(state.reviewState.reviews.size) { index ->
            ReviewCard(state.reviewState.reviews[index])
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}