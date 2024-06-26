package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.theme.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HVCategory(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .noRippleEffect { onClick() }
        .background(color = Theme.colors.secondary, shape = RoundedCornerShape(16.dp))
        .padding(vertical = 24.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.hvlogo),
            contentDescription = null,
            tint = Theme.colors.primary
        )

        Text(
            text = name,
            style = Theme.typography.labelLarge,
            color = Theme.colors.primaryShadesDark,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.basicMarquee()
        )
    }
}