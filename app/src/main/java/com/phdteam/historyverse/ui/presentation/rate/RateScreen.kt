package com.phdteam.historyverse.ui.presentation.rate

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.theme.Theme
import com.phdteam.historyverse.ui.theme.goldLight2
import com.phdteam.historyverse.ui.theme.yellowColor
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RateScreen(
    itemId: Int?,
    viewModel: RateViewModel = koinViewModel(parameters = { parametersOf(itemId) }),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    RateContent(state, viewModel, navigateBack = navigateBack)
    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}

private fun onEffect(effect: RateUiEffect?, context: Context) {

    when (effect) {
        RateUiEffect.OnRateError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
//        RateUiEffect.OnRateSubmitted -> navigateTo(RateUiEffect.OnRateSubmitted)
//        RateUiEffect.OnBackPresses -> navigateTo(RateUiEffect.OnBackPresses)

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RateContent(state: RateUiState, viewModel: RateViewModel, navigateBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Rate", color = Color(0xFF2F404F), style = Theme.typography.titleSmall)
                Text(text = "Post", color = goldLight2, style = Theme.typography.titleSmall)
            }
        }, navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back button",
                    tint = Color(0xFF2F404F)
                )
            }
        })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(56.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    repeat(5) { index ->
                        Icon(
                            painter = painterResource(id = if (index <= state.rate - 1) R.drawable.star_smooth else R.drawable.star_empty),
                            contentDescription = "rating",
                            modifier = Modifier
                                .size(28.dp)
                                .noRippleEffect { viewModel.onRateChange(index + 1) },
                            tint = yellowColor
                        )
                    }
                }

                TextField(
                    value = state.comment,
                    onValueChange = viewModel::onCommentChange,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    placeholder = { Text("Add comment") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
            if (state.isLoading)
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
