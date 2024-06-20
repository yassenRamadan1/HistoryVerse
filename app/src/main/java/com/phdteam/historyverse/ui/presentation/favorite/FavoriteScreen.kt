package com.phdteam.historyverse.ui.presentation.favorite

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.ItemCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = koinViewModel(),
    onClickCard: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    FavoriteContent(
        state = state,
        onClickCard = onClickCard,
        onNavigateBack = onNavigateBack,
        onClickFavorite = viewModel::onUpdateFavorite
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }

}

private fun onEffect(effect: FavoriteUiEffect?, context: Context) {

    when (effect) {
        FavoriteUiEffect.FavoriteError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}

@Composable
fun FavoriteContent(
    onClickFavorite: (id: Int) -> Unit,
    state: FavoriteUiState,
    onClickCard: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(

    ) {
        HVBackTopAppBar(onNavigateBack)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            contentPadding = PaddingValues(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                state.cards.size,
            ) { item ->
                ItemCard(
                    cardType = CardType.ARTIFACT,
                    state = state.cards[item],
                    onClickCard = {onClickCard() },
                    onClickFavorite = { onClickFavorite(item) }
                )
            }
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewFavoriteScreen() {

    val states = FavoriteUiState()
    val viewModel: FavoriteViewModel = koinViewModel()
    FavoriteContent(
        state = states,
        onClickCard = {},
        onNavigateBack = {},
        onClickFavorite = { }

    )

}
