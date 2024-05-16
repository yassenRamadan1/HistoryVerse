package com.phdteam.historyverse.ui.presentation.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.presentation.favorite.CardType
import com.phdteam.historyverse.ui.theme.Theme
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun FavoriteCard(
    musuemName: String = "Alex Musuem",
    cardTitleName: String = "HistoryVerse",
    ratingAvg: Double = 5.0,
    onClickCard: () -> Unit,
    onClickFavorite: () -> Unit,
    cardType: CardType = CardType.MUSEUM
) {

    Card(
        modifier = Modifier
            .clickable { onClickCard() }
            .padding(8.dp)
            .width(160.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_foreground),
            contentDescription = "",
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = cardTitleName,
                style = Theme.typography.labelMedium
            )

            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onClickFavorite }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

       if(cardType == CardType.PRODUCT||cardType == CardType.MUSEUM) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                repeat(ratingAvg.toInt()) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                }
                repeat(ceil(ratingAvg - ratingAvg.toInt()).toInt()) {
                    Image(
                        painter = painterResource(id = R.drawable.star_half),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                }
                repeat(floor(5 - ratingAvg).toInt()) {
                    Image(
                        painter = painterResource(id = R.drawable.star_empty),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    text = ratingAvg.toString(),
                    style = Theme.typography.labelMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        if(cardType == CardType.PRODUCT||cardType == CardType.ARTIFACT){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sheik_osama),
                    contentDescription = "",
                    modifier = Modifier
                        .size(18.dp)
                        .clip(shape = CircleShape)
                        .padding(end = 6.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = musuemName,
                    style = Theme.typography.labelMedium,
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))

    }
}

@Preview
@Composable
fun FavoriteCardPreview() {
    FavoriteCard(onClickCard = {},onClickFavorite= {})
}