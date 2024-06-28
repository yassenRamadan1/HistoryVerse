package com.phdteam.historyverse.ui.presentation.tickets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.play.integrity.internal.o
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.HVButton
import com.phdteam.historyverse.ui.components.HVTicketCard
import com.phdteam.historyverse.ui.presentation.details.DetailsViewModel
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun TicketsScreen(
    viewModel: TicketsViewModel = koinViewModel(),
//    detailsViewModel: DetailsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateTicket: () -> Unit
) {
//    val ticketsCount by detailsViewModel.bookedTicketsCount.collectAsState()
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    TicketsContent(
        states = state,
        onNavigateBack = onNavigateBack,
        onNavigateTicket = onNavigateTicket,
//        ticketsCount = ticketsCount
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(
    effect: TicketsUIEffect?,
    context: Context
) {
    when (effect) {
        TicketsUIEffect.TicketError -> Toast.makeText(
            context,
            "Error",
            Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}


@Composable
private fun TicketsContent(
    states: TicketsUIState,
    onNavigateBack: () -> Unit,
    onNavigateTicket: () -> Unit,
//    ticketsCount: Int
) {


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HVBackTopAppBar(onNavigateBack)
        Spacer(modifier = Modifier.padding(16.dp))


//        repeat(ticketsCount) {
            HVTicketCard(onNavigateTicket)
//        }


    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun TicketScreenPreview() {
//
//    val states = TicketsUIState()
//    TicketsContent(
//        states = states,
//        onNavigateBack = {},
//        onNavigateTicket = {},)
//}
//
