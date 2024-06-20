package com.phdteam.historyverse.ui.presentation.market.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.theme.goldDark3
import com.phdteam.historyverse.ui.theme.goldLight1
import java.util.logging.Filter

@Composable
fun FilterItem(filterName: String) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, color = goldDark3, shape = RoundedCornerShape(8.dp))
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = filterName, color = goldDark3)
        Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = goldLight1)
    }
}


@Preview
@Composable
private fun Preview() {
    FilterItem("Egyptian")
}