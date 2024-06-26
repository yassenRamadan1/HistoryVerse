package com.phdteam.historyverse.ui.presentation.seeall

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVAppBar
import com.phdteam.historyverse.ui.components.HVArtifact
import com.phdteam.historyverse.ui.components.HVMuseum
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SeeAllScreen(
    type: SeeAllType,
    viewModel: SeeAllViewModel = koinViewModel(parameters = { parametersOf(type) }),
    navigateTo: (id:Int) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    SeeAllContent(
        state = state,
        onBack = navigateBack,
        onclickItem =  navigateTo
    )

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: SeeAllUIEffect?, context: Context) {

    when (effect) {
        SeeAllUIEffect.SeeAllError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun SeeAllContent(
    state: SeeAllUIState,
    onBack: () -> Unit,
    onclickItem: (id:Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        HVAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = when (state.type) {
                SeeAllType.Artifacts -> stringResource(id = R.string.artifacts)
                SeeAllType.Museums -> stringResource(id = R.string.Museums)
                SeeAllType.NoThing -> ""
            },
            onBack = onBack
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.artifacts) { artifact ->
                    HVArtifact(
                        modifier = Modifier.fillMaxWidth(),
                        name = artifact.name,
                        rate = 4.0,
                        numberReviewers = 500,
                        profileUrl = artifact.imageUrl,
                        onClick = { onclickItem(artifact.id) }
                    )
                }

                items(state.museums) { museum ->
                    HVMuseum(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 215.dp),
                        name = museum.name,
                        address = museum.city,
                        imageUrl = museum.imageUrl,
                        onClick = { onclickItem(museum.id) }
                    )
                }
            }
        }
    }
}