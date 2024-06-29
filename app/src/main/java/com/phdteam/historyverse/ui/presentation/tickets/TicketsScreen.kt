package com.phdteam.historyverse.ui.presentation.tickets

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.HVTicketCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun TicketsScreen(
    viewModel: TicketsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateTicket: (id: Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    TicketsContent(
        states = state,
        onNavigateBack = onNavigateBack,
        onNavigateTicket = onNavigateTicket,
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context, onNavigateTicket)
        }
    }
}


private fun onEffect(
    effect: TicketsUIEffect?,
    context: Context,
    navigateTo: (id: Int) -> Unit
) {
}


@Composable
private fun TicketsContent(
    states: TicketsScreenUIState,
    onNavigateBack: () -> Unit,
    onNavigateTicket: (id: Int) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HVBackTopAppBar(onNavigateBack)
        Spacer(modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(states.tickets.size) { ticket ->
                HVTicketCard(
                    state = states.tickets[ticket],
                    onNavigateTicket = { onNavigateTicket(states.tickets[ticket].ticketId) }
                )
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TicketScreenPreview() {

    val states = TicketsScreenUIState()
    TicketsContent(
        states = states,
        onNavigateBack = {},
        onNavigateTicket = {},
    )
}

