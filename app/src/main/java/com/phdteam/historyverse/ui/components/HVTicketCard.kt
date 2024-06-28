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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.theme.Theme

@Composable
fun HVTicketCard(
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
                text = "Alex Museum",
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
                text = "4515462",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )
            Text(
                text = "23/03/2023",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )

        }
    }

}


