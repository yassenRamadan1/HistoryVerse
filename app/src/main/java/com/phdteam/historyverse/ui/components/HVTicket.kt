package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.presentation.ticket.TicketScreenUIState
import com.phdteam.historyverse.ui.theme.Theme

@Composable
fun HVTicket(
    state: TicketScreenUIState = TicketScreenUIState(),
) {
    Box(modifier = Modifier.fillMaxSize(0.8f)) {
        Image(
            painter = painterResource(id = R.drawable.ticket),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = state.ticket.museumName,
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesDark,
                        fontSize = 20.sp
                    )
                    Text(
                        text = state.ticket.locationName,
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.secondaryShadesDark,
                        fontSize = 16.sp
                    )
                }
                Icon(painter = painterResource(id = R.drawable.room), contentDescription = "")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Visitor Name",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesDark,
                        fontSize = 16.sp
                    )
                    Text(
                        text = state.ticket.visitorName,
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.secondaryShadesDark,
                        fontSize = 12.sp
                    )
                }
                Column {
                    Text(
                        text = "Ticket Number",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesDark,
                        fontSize = 16.sp
                    )
                    Text(
                        text = state.ticket.ticketNumber,
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.secondaryShadesDark,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Ticket Type",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesDark,
                        fontSize = 16.sp
                    )
                    Text(
                        text = state.ticket.ticketType,
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.secondaryShadesDark,
                        fontSize = 16.sp
                    )
                }
                Column {
                    Text(
                        text = "Visit Date",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesDark,
                        fontSize = 18.sp
                    )
                    Text(
                        text = state.ticket.visitDate,
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.secondaryShadesDark,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.70f))
            Image(
                painter = painterResource(id = R.drawable.barcode),
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = ""
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HVTicketPreview() {
    HVTicket(
    )
}
