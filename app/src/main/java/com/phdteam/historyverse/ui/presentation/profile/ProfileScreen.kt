package com.phdteam.historyverse.ui.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.ProfileOptionButton
import com.phdteam.historyverse.ui.theme.Gold
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    ProfileContent(
        state = state, onClickOption = {}
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: ProfileUIEffect?, context: Context) {

    when (effect) {
        ProfileUIEffect.ProfileError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun ProfileContent(
    state: ProfileUIState,
    onClickOption: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gold),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = R.drawable.sheik_osama),
                    contentDescription = "",
                    modifier = Modifier
                        .size(164.dp)
                        .clip(CircleShape)
                        .padding(24.dp),
                    contentScale = ContentScale.Crop
                )

                Button(onClick = { { /*TODO*/ } },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.wrapContentSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.cam_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(48.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Osama bin ladin",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "+911",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }


        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        )
        {
            Spacer(modifier = Modifier.height(24.dp))
            ProfileOptionButton(
                text = "Saved",
                onClickOption = { },
                painter = R.drawable.back_arrow,
            )

            ProfileOptionButton(
                text = "Favorite",
                onClickOption = { },
                painter = R.drawable.back_arrow
            )
            ProfileOptionButton(
                text = "Cart",
                onClickOption = { },
                painter = R.drawable.back_arrow
            )
            ProfileOptionButton(
                text = "My ticket",
                onClickOption = { },
                painter = R.drawable.back_arrow
            )
            ProfileOptionButton(
                text = "Logout",
                onClickOption = { },
                painter = R.drawable.baseline_logout_24
            )
        }







        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "Profile screen",
                style = Theme.typography.bodyLarge,
                color = Theme.colors.primary
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewProfileScreen() {

    val states = ProfileUIState()
    ProfileContent(state = states, onClickOption = {})
}
