package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.presentation.details.Review
import com.phdteam.historyverse.ui.theme.Theme
import com.phdteam.historyverse.ui.theme.yellowColor

@Composable
fun ReviewCard(review : Review) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp) , modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(review.userImageUrl) ,
                contentDescription = null ,
                modifier = Modifier.size(40.dp).clip(CircleShape) ,
                contentScale = ContentScale.Crop
            )
            Text(
                review.userName ,
                style = Theme.typography.titleSmall ,
                fontSize = 16.sp ,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row(modifier = Modifier) {
            for (i in 1 .. 5) {
                Icon(
                    painter = painterResource(if (i <= review.rating) R.drawable.star else R.drawable.rating_empty_star) ,
                    contentDescription = null ,
                    modifier = Modifier.size(10.dp),
                    tint = yellowColor
                )
            }
        }
        Text(review.review , style = Theme.typography.bodyMedium)
        Text(
            review.date ,
            modifier = Modifier.align(Alignment.End) ,
            style = Theme.typography.labelLarge ,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
private fun preview() {
    ReviewCard(Review())
}