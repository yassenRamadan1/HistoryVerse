package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phdteam.historyverse.ui.modifier.noRippleEffect


@Composable
fun ProfileOptionButton(
    text: String,
    onClickOption: () -> Unit,
    painter: Int,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .noRippleEffect { onClickOption() }
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
            ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
        Image(
            painter = painterResource(id = painter),
            contentDescription = "",
            modifier = Modifier
                .graphicsLayer(rotationZ = 180.0F)
        )
    }
}

@Preview
@Composable
fun ProfileOptionButtonPreview() {
    ProfileOptionButton(
        text = "Option",
        onClickOption = {},
        painter = android.R.drawable.arrow_down_float
    )
}