package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.presentation.details.DetailsUiState

@Composable
fun ProductsTab(modifier : Modifier = Modifier , state : DetailsUiState) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Text(
                "Recommended Products" ,
                style = MaterialTheme.typography.bodyMedium ,
                fontWeight = FontWeight.SemiBold
            )
        }
        item {
            LazyRow(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.recommendedArtifacts.size) { index ->
                    ArtifactCard(state.recommendedArtifacts[index])
                }
            }
        }
        item {
            Text(
                "Most Sold" ,
                style = MaterialTheme.typography.bodyMedium ,
                fontWeight = FontWeight.SemiBold
            )

        }
        item {
            LazyRow(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.mostPopularArtifacts.size) { index ->
                    ArtifactCard(state.mostPopularArtifacts[index])
                }

            }

        }
    }
}