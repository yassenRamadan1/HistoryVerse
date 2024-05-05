package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.theme.Theme


@Composable
fun HVTitleWithSeeAll(
    title: String,
    modifier: Modifier = Modifier,
    showSeeAll: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = title,
            color = Theme.colors.primaryShadesDark,
            style = Theme.typography.titleSmall,
        )

        Spacer(modifier = Modifier.weight(1f))

        if (showSeeAll) {
            Row(
                modifier = Modifier.noRippleEffect { onClick() },
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "See All",
                    style = Theme.typography.bodyMedium,
                    color = Theme.colors.ternaryShadesDark,
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTitleWithSeeAll() {
    HVTitleWithSeeAll(
        title = "USER",
        onClick = {}
    )
}
