package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.rememberAsyncImagePainter
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIState

@Composable
fun ProfilePicData(
    state: ProfileUIState,
    onclickProfilePic: ()-> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        Box(contentAlignment = Alignment.BottomEnd) {

            if (state.imageUri.isNotBlank()) {
            Image(
                painter = rememberAsyncImagePainter(model = state.imageUri),
                contentDescription = "",
                modifier = Modifier
                    .size(164.dp)
                    .padding(24.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.default_prfile),
                contentDescription = "",
                modifier = Modifier
                    .size(164.dp)
                    .padding(24.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        }
                Image(
                    painter = painterResource(id = R.drawable.cam_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .noRippleEffect { onclickProfilePic()}
                        .padding(24.dp),
                    contentScale = ContentScale.Crop
                )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = state.name,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = state.phone,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }

}

@Preview
@Composable
fun ProfilePicDataPreview() {
    ProfilePicData(
        state = ProfileUIState(),
        onclickProfilePic = {}
    )
}