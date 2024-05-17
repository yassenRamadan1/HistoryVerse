package com.phdteam.historyverse.ui.presentation.favorite

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.presentation.favorite.components.ItemCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = koinViewModel(),
    onClickCard: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    FavoriteContent(
        state = state, onClickCard = onClickCard
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
    state: FavoriteUiState,
    onClickCard: () -> Unit
) {
    LazyColumn(
            modifier = Modifier,
    contentPadding = PaddingValues(horizontal = 32.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            state.card.size,
        ) { item ->
            ItemCard(
                state = state.card[item],
                onClickCard = { /* TODO */ },
                onClickFavorite = { /* TODO */ }
            )
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewFavoriteScreen() {

    val states = FavoriteUiState()
    FavoriteContent(state = states, onClickCard = {})
}
