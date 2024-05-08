package com.phdteam.historyverse.ui.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.phdteam.historyverse.ui.presentation.details.Artifact
import com.phdteam.historyverse.ui.theme.greyColorText

@Composable
fun ArtifactCard(item : Artifact) {
    Column(modifier = Modifier.size(110.dp).clip(RoundedCornerShape(topStart = 12.dp , topEnd = 12.dp))) {
        Image(
            painter = rememberAsyncImagePainter(item.image) ,
            contentDescription = null ,
            contentScale = ContentScale.Fit ,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.7f)
        )
        Text(item.name , modifier = Modifier.padding(8.dp) , color = greyColorText)

    }
}