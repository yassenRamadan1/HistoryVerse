package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.layout.*
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
import com.phdteam.historyverse.ui.presentation.details.Artifact
import com.phdteam.historyverse.ui.presentation.details.DetailsUiState
import com.phdteam.historyverse.ui.theme.Theme
import com.phdteam.historyverse.ui.theme.yellowColor

@Composable
fun ReviewTab(
    modifier: Modifier = Modifier,
    state: DetailsUiState,
    onItemClick: (rate: Int) -> Unit
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
                            painter = if (i <= state.review) painterResource(R.drawable.star) else painterResource(
                                R.drawable.rating_empty_star
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .noRippleEffect { onItemClick(i) },
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
        items(state.reviews.size) { index ->
            ReviewCard(state.reviews[index])
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}