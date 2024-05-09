package com.phdteam.historyverse.ui.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
    Column {

    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewFavoriteScreen() {

    val states = FavoriteUiState()
    FavoriteContent(state = states, onClickCard = {})
}
