package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.theme.Theme

@Composable
fun HVPaymentCard() {
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.BottomStart
    )
    {
        Image(
            painter = painterResource(id = R.drawable.visa),
            contentDescription = null
        )
        Card(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
        ) {
            Text(
                text = "2244 4223 5342 2224",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesLight
            )
            Text(
                text = "05/25",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesLight
            )
            Text(
                text = "Abdelrahman Abdelwahab",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesLight
            )

        }
    }
}