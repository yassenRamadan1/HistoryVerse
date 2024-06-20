package com.phdteam.historyverse.ui.presentation.market.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.market.FilterItem
import com.phdteam.historyverse.ui.presentation.market.FilterItemColors
import com.phdteam.historyverse.ui.theme.goldDark3
import com.phdteam.historyverse.ui.theme.goldLight1

@Composable
fun SelectedFilterItem(
    filterItem: FilterItem,
    onFilterClick: (FilterItem) -> Unit
) {
    val selectedColors = FilterItemColors(
        border = goldDark3,
        iconColor = goldLight1,
        nameColor = goldDark3
    )
    val unselectedColors = FilterItemColors(
        border = Color.DarkGray,
        iconColor = Color.LightGray,
        nameColor = Color.DarkGray
    )
    val colors = if (filterItem.isSelected) selectedColors else unselectedColors
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, color = colors.border, shape = RoundedCornerShape(8.dp))
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .noRippleEffect { onFilterClick(filterItem) },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = filterItem.name, color = colors.nameColor)
        Icon(
            imageVector = if (filterItem.isSelected) Icons.Filled.Clear else Icons.Filled.Add,
            contentDescription = null,
            tint = colors.iconColor
        )
    }
}


@Preview
@Composable
private fun Preview() {
    SelectedFilterItem(FilterItem(), {})
}