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
import com.phdteam.historyverse.ui.presentation.details.DetailsUiState

@Composable
fun ArtifatsTab(modifier : Modifier = Modifier , state : DetailsUiState) {
    LazyColumn(modifier = modifier.fillMaxSize() , verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Column(modifier = Modifier.fillMaxWidth() , verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Recommended Artifacts" ,
                    style = MaterialTheme.typography.bodyMedium ,
                    fontWeight = FontWeight.SemiBold
                )
                LazyRow(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.recommendedArtifacts.size) { index ->
                        ArtifactCard(state.recommendedArtifacts[index])
                    }
                }
            }
        }
        item {
            Column(modifier = Modifier.fillMaxWidth() , verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Text(
                    "Most popular" ,
                    style = MaterialTheme.typography.bodyMedium ,
                    fontWeight = FontWeight.SemiBold
                )
                LazyRow(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.mostPopularArtifacts.size) { index ->
                        ArtifactCard(state.mostPopularArtifacts[index])
                    }

                }
            }
        }

    }

}