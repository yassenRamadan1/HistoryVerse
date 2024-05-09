package com.phdteam.historyverse.ui.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.phdteam.historyverse.ui.presentation.profile.ProfileContent
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = koinViewModel()
){

}

@Composable
fun FavoriteContent(
    state: FavoriteUiState,
    onClickCard: () -> Unit
){


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewFavoriteScreen() {

    val states = FavoriteUiState()
    FavoriteContent(state = states, onClickCard = {})
}
