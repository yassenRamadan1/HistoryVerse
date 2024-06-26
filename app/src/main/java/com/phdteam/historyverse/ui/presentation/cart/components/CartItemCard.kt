package com.phdteam.historyverse.ui.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.phdteam.historyverse.ui.presentation.cart.CartItemUiState
import com.phdteam.historyverse.ui.theme.Theme
import kotlin.math.ceil

@Composable
fun CartItemCard(item: CartItemUiState) {
    val price = if (ceil(item.price - item.price.toInt()) != 0.0) item.price else item.price.toInt()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Theme.colors.background)
            .clip(shape = RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(item.imageUrl).build(),
            contentDescription = "item Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name, color = Color(0xFF2F404F))
            Text(text = "${price} EGP", color = Color(0xFFCCCFC7))
        }
    }

}
