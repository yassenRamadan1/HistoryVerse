package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.presentation.ticket.Ticket
import com.phdteam.historyverse.ui.presentation.ticket.TicketScreenUIState
import com.phdteam.historyverse.ui.theme.Theme

@Composable
fun HVTicketCard(
    state: Ticket = Ticket(),
    onNavigateTicket: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onNavigateTicket)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = state.museumName,
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )
            Icon(
                painter = painterResource(id = R.drawable.room),
                contentDescription = ""
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = state.ticketNumber,
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )
            Text(
                text = state.visitDate,
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )

        }
    }

}


