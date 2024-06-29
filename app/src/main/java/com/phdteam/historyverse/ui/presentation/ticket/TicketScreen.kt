package com.phdteam.historyverse.ui.presentation.ticket

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.HVButton
import com.phdteam.historyverse.ui.components.HVTicket
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun TicketScreen(
    id: Int? = 0,
    onNavigateBack: () -> Unit,
    viewModel: TicketViewModel = koinViewModel(parameters = { parametersOf(id) }),
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    TicketContent(
        states = state,
        onNavigateBack = onNavigateBack,
        onClickDownload = {}
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(
    effect: TicketUIEffect?,
    context: Context
) {
    when (effect) {
        TicketUIEffect.TicketError -> Toast.makeText(
            context,
            "Error",
            Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}


@Composable
private fun TicketContent(
    states: TicketScreenUIState,
    onNavigateBack: () -> Unit,
    onClickDownload: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HVBackTopAppBar(onNavigateBack)
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )

        {
            Text(
                text = "Your Ticket Booking is Confirmed",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )
            Icon(
                painter = painterResource(id = R.drawable.checkmark),
                contentDescription = "",
                tint = Color.Green
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        HVTicket(state = states)

        Spacer(modifier = Modifier.padding(16.dp))

        HVButton(
            title = "Download",
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TicketScreenPreview() {

    val states = TicketScreenUIState()
    TicketContent(
        states = states,
        onNavigateBack = {},
        onClickDownload = {})
}

