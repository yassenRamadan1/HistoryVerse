package com.phdteam.historyverse.ui.presentation.market.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.presentation.market.FilterItem
import com.phdteam.historyverse.ui.presentation.market.MarketFilterType
import com.phdteam.historyverse.ui.theme.Theme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MarketBottomSheetContent(filters: List<FilterItem>, onFilterClick: (FilterItem) -> Unit) {
    val cultureFilters = filters.filter { it.type == MarketFilterType.Culture }
    val categoriesFilters = filters.filter { it.type == MarketFilterType.Categories }
    Text(
        text = "Culture",
        style = Theme.typography.titleSmall,
        modifier = Modifier.padding(start = 16.dp)
    )
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
    ) {
        repeat(cultureFilters.size) {
            SelectedFilterItem(
                filterItem = cultureFilters[it],
                onFilterClick = onFilterClick
            )
        }
    }
    Text(
        text = "Categories",
        style = Theme.typography.titleSmall,
        modifier = Modifier.padding(start = 16.dp)
    )
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
    ) {
        repeat(categoriesFilters.size) {
            SelectedFilterItem(
                filterItem = categoriesFilters[it],
                onFilterClick = onFilterClick
            )
        }
    }
}