package com.phdteam.historyverse.ui.presentation.market.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.market.MarketItem
import com.phdteam.historyverse.ui.theme.Theme
import com.phdteam.historyverse.ui.theme.goldDark4
import com.phdteam.historyverse.ui.theme.goldLight1
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun MarketProductItem(item: MarketItem, onItemClick: (id: Int) -> Unit) {
    val rating = item.rating
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(.5f)
            .background(Color.Transparent)
            .clip(
                RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                )
            )
            .noRippleEffect { onItemClick(item.id) },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = item.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(goldLight1),
            contentScale = ContentScale.FillBounds

        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (item.name.length > 13) "${
                    item.name.substring(
                        0,
                        13
                    )
                }.." else item.name,
            )
            Text(text = item.price, color = goldDark4, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            repeat(rating.toInt()) {
                Image(
                    painter = painterResource(id = R.drawable.star_smooth),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
            repeat(ceil(rating - rating.toInt()).toInt()) {
                Image(
                    painter = painterResource(id = R.drawable.star_half),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
            repeat(floor(5 - rating).toInt()) {
                Image(
                    painter = painterResource(id = R.drawable.star_empty),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = rating.toString(),
                style = Theme.typography.labelMedium
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.shopImage),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(goldLight1)
            )
            Text(text = item.shopName)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MarketProductItem(
        MarketItem(
            name = "Product Name",
            price = "100",
            image = "",
            rating = 4.5,
            shopName = "Shop Name",
            shopImage = ""
        ), {}
    )
}