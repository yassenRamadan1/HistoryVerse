package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.theme.Theme

@Composable
fun HVChip(city: String, isSelected: Boolean, onSelectedChanged: (Boolean) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onSelectedChanged(!isSelected) },
        color = if (isSelected) Theme.colors.primary else Theme.colors.ternaryShadesDark,
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = city,
            modifier = Modifier.padding(8.dp),
            color = if (isSelected) Theme.colors.secondaryShadesLight else Theme.colors.primaryShadesLight
        )
    }
}